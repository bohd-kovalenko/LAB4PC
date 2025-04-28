import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import command.CommandType
import command.ErrorCommand
import command.InitialDataCommand
import data.ComputationalData
import response.CalculationStartedResponse
import response.ErrorResponse
import response.InitialDataResponse
import response.OperationStatus
import response.Response
import response.ResultResponse
import java.io.BufferedInputStream
import java.net.Socket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadLocalRandom

class Handler {
    private companion object {
        val jsonMapper: ObjectMapper = ObjectMapper().apply {
            disable(JsonParser.Feature.AUTO_CLOSE_SOURCE)
            registerModule(KotlinModule())
        }
        const val READ_LIMIT = 2
    }

    private var data: ComputationalData? = null
    private var result: CompletableFuture<Array<IntArray>>? = null

    fun handle(socket: Socket) {
        val inputStream = BufferedInputStream(socket.getInputStream())
        val outputStream = socket.getOutputStream()
        inputStream.mark(READ_LIMIT)
        while (inputStream.read() != -1) {
            inputStream.reset()
            val command = runCatching {
                jsonMapper.readValue(inputStream, Command::class.java)
            }.getOrElse {
                ErrorCommand()
            }
            val response = processCommand(command)
            outputStream.write(jsonMapper.writeValueAsBytes(response))
            inputStream.mark(READ_LIMIT)
        }
    }

    fun processCommand(command: Command): Response {
        return when (command.commandType()) {
            CommandType.INITIAL_DATA -> {
                val initialDataCommand = command as InitialDataCommand
                data = initialDataCommand.computationalData
                InitialDataResponse("Initial data received")
            }

            CommandType.START_CALCULATION -> {
                if (data == null) {
                    return ErrorResponse("No initial data provided")
                }
                val computationalData = data!!
                val pool = Executors.newFixedThreadPool(computationalData.threadCount)
                result = generateMatrix(
                    computationalData.matrixSize,
                    computationalData.threadCount,
                    computationalData.maxCellNumber,
                    pool
                )
                CalculationStartedResponse("Calculation started")
            }

            CommandType.GET_RESULT -> {
                if (result == null) {
                    return ErrorResponse("Calculation not started")
                }
                if (!result!!.isDone) {
                    return ResultResponse(OperationStatus.IN_PROGRESS, null)
                }
                return ResultResponse(
                    OperationStatus.DONE,
                    result!!.get()
                )
            }

            CommandType.ERROR -> ErrorResponse("Error")
        }
    }

    fun generateMatrix(size: Int, threadCount: Int, maxNumber: Int, pool: ExecutorService): CompletableFuture<Array<IntArray>> {
        val matrix = Array(size) { IntArray(size) }
        val columnsPerThread = size / threadCount
        val futures = mutableListOf<CompletableFuture<Void>>()

        for (threadIndex in 0 until threadCount) {
            val startColumn = threadIndex * columnsPerThread
            val endColumn = if (threadIndex == threadCount - 1) size else (threadIndex + 1) * columnsPerThread

            val future = CompletableFuture.runAsync({
                for (column in startColumn until endColumn) {
                    var product = 1
                    for (row in 0 until size) {
                        if (row != size - 1 - column) {
                            matrix[row][column] = ThreadLocalRandom.current().nextInt(maxNumber)
                            product *= matrix[row][column]
                        }
                    }
                    matrix[size - 1 - column][column] = product
                }
            }, pool)

            futures.add(future)
        }

        return CompletableFuture.allOf(*futures.toTypedArray())
            .thenApply { matrix }
    }
    }