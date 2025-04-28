import command.CommandType
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import command.GetResultCommand
import command.InitialDataCommand
import command.StartCalculationCommand

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "commandType")
@JsonSubTypes(
    JsonSubTypes.Type(value = InitialDataCommand::class, name = "INITIAL_DATA"),
    JsonSubTypes.Type(value = StartCalculationCommand::class, name = "START_CALCULATION"),
    JsonSubTypes.Type(value = GetResultCommand::class, name = "GET_RESULT")
)
fun interface Command {
    fun commandType(): CommandType
}