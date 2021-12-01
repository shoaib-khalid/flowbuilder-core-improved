package com.kalsym.flowbuilder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class FlowbuilderApplication {

    public static String VERSION;

    public static void main(String[] args) {
        SpringApplication.run(FlowbuilderApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(ApplicationContext context) {
        return args -> {
//            VERSION = version;
//
//            logger.info("[v{}][{}] {}", VERSION, "", "\n"
//                    + "                       _ _                            _          \n"
//                    + "                      (_) |                          (_)         \n"
//                    + "   ___ _ __ ___   __ _ _| |______ ___  ___ _ ____   ___  ___ ___ \n"
//                    + "  / _ \\ '_ ` _ \\ / _` | | |______/ __|/ _ \\ '__\\ \\ / / |/ __/ _ \\\n"
//                    + " |  __/ | | | | | (_| | | |      \\__ \\  __/ |   \\ V /| | (_|  __/\n"
//                    + "  \\___|_| |_| |_|\\__,_|_|_|      |___/\\___|_|    \\_/ |_|\\___\\___|\n"
//                    + "                                                                 \n"
//                    + "                                                                 "
//            );
        };
    }

}
