package org.example.response;

public record Response(OperationStatus status,
                       int[][] result,
                       String message) {

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Response {\n");
//        sb.append("  status: ").append(status).append(",\n");
//        sb.append("  result: ");
//        if (result == null) {
//            sb.append("null");
//        } else {
//            sb.append("[\n");
//            for (int i = 0; i < result.length; i++) {
//                sb.append("    ");
//                if (result[i] == null) {
//                    sb.append("null");
//                } else {
//                    sb.append("[");
//                    for (int j = 0; j < result[i].length; j++) {
//                        sb.append(result[i][j]);
//                        if (j < result[i].length - 1) {
//                            sb.append(", ");
//                        }
//                    }
//                    sb.append("]");
//                }
//                if (i < result.length - 1) {
//                    sb.append(",");
//                }
//                sb.append("\n");
//            }
//            sb.append("  ]");
//        }
//        sb.append(",\n");
//
//        sb.append("  message: ");
//        if (message == null) {
//            sb.append("null");
//        } else {
//            sb.append("\"").append(message).append("\"");
//        }
//        sb.append("\n}");
//
//        return sb.toString();
//    }
}