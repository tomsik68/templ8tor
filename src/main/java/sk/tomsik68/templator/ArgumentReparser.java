package sk.tomsik68.templator;

import java.util.HashMap;
import java.util.Map;

final class ArgumentReparser {

    private ArgumentReparser(){
        throw new AssertionError("");
    }


    public static Map<String, Object> reparse(String[] args) {
        Map<String, Object> result = new HashMap<>();
        String[] newArgs = removeFirst(args);
        // TODO: reparse with respecting quotes
        //String argsString = join(newArgs, " ");

        for(String arg : newArgs) {
            String[] values = arg.split("=");
            result.put(values[0], values[1]);
        }
        return result;
    }

    private static String join(String[] newArgs, String joiner) {
        StringBuilder argsStringBuilder = new StringBuilder();
        for (String arg : newArgs) {
            argsStringBuilder.append(arg).append(joiner);
        }
        return argsStringBuilder.toString();
    }

    private static String[] removeFirst(String[] args) {
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, newArgs.length);
        return newArgs;
    }
}
