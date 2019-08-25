package de.team33.lena.rpg;

public class Main {

    public static void main (String[] args){
        if(args.length == 2) {
            String command = (args[0] + "_" + args[1]).toUpperCase();
            Command.valueOf(command).run();

        } else {
            System.out.println("Command missing");
            // possible commands... etc HELP :D
        }

    }

    private enum Command {

        ADD_CHARACTER(new AddCharacterCommand());

        private final Runnable impl;

        Command(Runnable impl) {
            this.impl = impl;
        }

        public void run() {
            impl.run();
        }

    }
}
