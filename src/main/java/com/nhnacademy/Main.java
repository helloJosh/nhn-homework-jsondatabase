package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.nhnacademy.servicevalidator.ItemServiceValidator;
import com.nhnacademy.servicevalidator.MatchService;
import com.nhnacademy.servicevalidator.MemberServiceValidator;
import com.nhnacademy.servicevalidator.UpdateHistoryService;

public class Main {
    public static final int OPTION_COUNT = 18;
    public static void main(String[] args) {
        ItemServiceValidator itemService = new ItemServiceValidator();
        MatchService matchService = new MatchService();
        MemberServiceValidator memberService = new MemberServiceValidator();
        UpdateHistoryService updateHistory = new UpdateHistoryService();


        Options options = new Options();
        Option[] optionArray = new Option[OPTION_COUNT];
        optionInit(optionArray, options);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption(optionArray[10].getOpt())||commandLine.hasOption(optionArray[10].getLongOpt())) {
                // help
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("recoder", options);
            } else if (commandLine.hasOption(optionArray[17].getOpt())||commandLine.hasOption(optionArray[17].getLongOpt())) {
                //db- file path
                String filePath = commandLine.getOptionValue(optionArray[17].getOpt());
                JsonFileWorker.setFilePath(filePath);
            } else{
                throw new IllegalArgumentException("데이터베이스 파일이 지정이 안됐습니다.");
            }
            

            if (commandLine.hasOption(optionArray[0].getOpt())||commandLine.hasOption(optionArray[0].getLongOpt())) {
                // add
                if (commandLine.hasOption(optionArray[4].getOpt())||commandLine.hasOption(optionArray[4].getLongOpt())) {
                    String type = commandLine.getOptionValue(optionArray[4].getOpt());
                    if(type.equals("user")){
                        if(userSaveHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            String name = commandLine.getOptionValue(optionArray[6].getOpt());
                            memberService.save(id, name);
                        } else {
                            System.out.println("값이 부족합니다.");
                        }
                    } else if(type.equals("item")){
                        if(itemSaveHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            String model = commandLine.getOptionValue(optionArray[6].getOpt());
                            int energy = Integer.parseInt(commandLine.getOptionValue(optionArray[11].getOpt()));
                            int attack =Integer.parseInt(commandLine.getOptionValue(optionArray[12].getOpt()));
                            int defense =Integer.parseInt(commandLine.getOptionValue(optionArray[13].getOpt()));
                            int movingSpeed =Integer.parseInt(commandLine.getOptionValue(optionArray[14].getOpt()));
                            int attackSpeed =Integer.parseInt(commandLine.getOptionValue(optionArray[15].getOpt()));
                            itemService.save(id, model, energy, attack, defense, movingSpeed, attackSpeed);
                        } else {
                            System.out.println("값이 부족합니다.");
                        }
                    } else if(type.equals("match")){
                        if(matchSaveHasOption(commandLine, optionArray)){
                            int count = Integer.parseInt(commandLine.getOptionValue(optionArray[8].getOpt()));
                            int win = Integer.parseInt(commandLine.getOptionValue(optionArray[9].getOpt()));
                            matchService.save(count, win);
                        } else {
                            System.out.println("값이 부족합니다.");
                        }
                    }
                }

            }
            else if (commandLine.hasOption(optionArray[1].getOpt())||commandLine.hasOption(optionArray[1].getLongOpt())) {
                // delete
                if (commandLine.hasOption(optionArray[4].getOpt())||commandLine.hasOption(optionArray[4].getLongOpt())) {
                    String type = commandLine.getOptionValue(optionArray[4].getOpt());
                    if(type.equals("user")){
                        if(userDeleteHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            memberService.remove(id);
                        }else{
                            System.out.println("값이 부족합니다.");
                        }
                    } else if(type.equals("item")){
                        if(itemDeleteHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            itemService.remove(id);
                        }else{
                            System.out.println("값이 부족합니다.");
                        }
                    } 
                } 
            }
            else if (commandLine.hasOption(optionArray[2].getOpt())||commandLine.hasOption(optionArray[2].getLongOpt())) {
                // update

                if (commandLine.hasOption(optionArray[4].getOpt())||commandLine.hasOption(optionArray[4].getLongOpt())) {
                    // type
                    String type = commandLine.getOptionValue(optionArray[4].getOpt());
                    if(type.equals("user")){
                        if(userSaveHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            String name = commandLine.getOptionValue(optionArray[6].getOpt());
                            memberService.update(id, name);
                        } else{
                            System.out.println("값이 부족합니다.");
                        }
                    } else if(type.equals("item")){
                        if(itemSaveHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            String model = commandLine.getOptionValue(optionArray[6].getOpt());
                            int energy = Integer.parseInt(commandLine.getOptionValue(optionArray[11].getOpt()));
                            int attack =Integer.parseInt(commandLine.getOptionValue(optionArray[12].getOpt()));
                            int defense =Integer.parseInt(commandLine.getOptionValue(optionArray[13].getOpt()));
                            int movingSpeed =Integer.parseInt(commandLine.getOptionValue(optionArray[14].getOpt()));
                            int attackSpeed =Integer.parseInt(commandLine.getOptionValue(optionArray[15].getOpt()));
                            itemService.update(id, model, energy, attack, defense, movingSpeed, attackSpeed);
                        }else{
                            System.out.println("값이 부족합니다.");
                        }
                    } 
                }
            }
            else if (commandLine.hasOption(optionArray[3].getOpt())||commandLine.hasOption(optionArray[3].getLongOpt())) {
                // find
                if (commandLine.hasOption(optionArray[4].getOpt())||commandLine.hasOption(optionArray[4].getLongOpt())) {
                    String type = commandLine.getOptionValue(optionArray[4].getOpt());
                    if(type.equals("user")){
                        if(userDeleteHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            memberService.findById(id);
                        }else{
                            System.out.println("값이 부족합니다.");
                        }
                    } else if(type.equals("item")){
                        if(itemDeleteHasOption(commandLine, optionArray)){
                            int id = Integer.parseInt(commandLine.getOptionValue(optionArray[5].getOpt()));
                            itemService.findById(id);
                        }else{
                            System.out.println("값이 부족합니다.");
                        }
                    }    
                }
            }
            else if (commandLine.hasOption(optionArray[7].getOpt())||commandLine.hasOption(optionArray[7].getLongOpt())) {
                // list
                if (commandLine.hasOption(optionArray[4].getOpt())||commandLine.hasOption(optionArray[4].getLongOpt())) {
                    String type = commandLine.getOptionValue(optionArray[4].getOpt());
                    if(type.equals("user")){
                        memberService.findAll();
                    } else if(type.equals("item")){
                        itemService.findAll();
                    } else if(type.equals("match")){
                        matchService.findAll();
                    } else if(type.equals("history")){
                        updateHistory.findAll();
                    }
                }
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage()+" 매개변수가 없습니다. 매개변수를 입력해주세요.");
            System.exit(0);
        }

    }
    public static boolean userSaveHasOption(CommandLine commandLine ,Option[] optionArray){
        return (commandLine.hasOption(optionArray[5].getOpt())||commandLine.hasOption(optionArray[5].getLongOpt()))
            && (commandLine.hasOption(optionArray[6].getOpt())||commandLine.hasOption(optionArray[6].getLongOpt()));
    }
    public static boolean matchSaveHasOption(CommandLine commandLine ,Option[] optionArray){
        return (commandLine.hasOption(optionArray[8].getOpt())||commandLine.hasOption(optionArray[8].getLongOpt()))
            && (commandLine.hasOption(optionArray[9].getOpt())||commandLine.hasOption(optionArray[9].getLongOpt()));
    }
    public static boolean itemSaveHasOption(CommandLine commandLine ,Option[] optionArray){
        return (commandLine.hasOption(optionArray[5].getOpt())||commandLine.hasOption(optionArray[5].getLongOpt()))
            && (commandLine.hasOption(optionArray[6].getOpt())||commandLine.hasOption(optionArray[6].getLongOpt()))
            && (commandLine.hasOption(optionArray[11].getOpt())||commandLine.hasOption(optionArray[11].getLongOpt()))
            && (commandLine.hasOption(optionArray[12].getOpt())||commandLine.hasOption(optionArray[12].getLongOpt()))
            && (commandLine.hasOption(optionArray[13].getOpt())||commandLine.hasOption(optionArray[13].getLongOpt()))
            && (commandLine.hasOption(optionArray[14].getOpt())||commandLine.hasOption(optionArray[14].getLongOpt()))
            && (commandLine.hasOption(optionArray[15].getOpt())||commandLine.hasOption(optionArray[15].getLongOpt()));
    }
    public static boolean userDeleteHasOption(CommandLine commandLine ,Option[] optionArray){
        return (commandLine.hasOption(optionArray[5].getOpt())||commandLine.hasOption(optionArray[5].getLongOpt()));
    }
    public static boolean itemDeleteHasOption(CommandLine commandLine ,Option[] optionArray){
        return (commandLine.hasOption(optionArray[5].getOpt())||commandLine.hasOption(optionArray[5].getLongOpt()));
    }
    public static void optionInit(Option[] optionArray, Options options){
        optionArray[0] = new Option("a","add", false, "데이터를 추가합니다.");
        optionArray[1] = new Option("D","delete", false, "데이터 삭제");
        optionArray[2] = new Option("u","update", false, "데이터 변경");
        optionArray[3] = new Option("s","search", false, "특정 데이터 찾기");
        optionArray[4] = new Option("t","type", true, "데이터의 종류를 지정합니다.");
        optionArray[5] = new Option("i","id", true, "아이디");
        optionArray[6] = new Option("n","name", true, "이름");
        optionArray[7]= new Option("l","list", false, "목록을 보여줍니다.");
        optionArray[8] = new Option("c","count", true, "대전 횟수");
        optionArray[9] = new Option("W","Win", true, "승리 횟수");
        optionArray[10] = new Option("h","help", false, "도움말");
        optionArray[11] = new Option("e","energy", true, "체력");
        optionArray[12] = new Option("A","attack", true, "공격력");
        optionArray[13] = new Option("d","defense", true, "방어력");
        optionArray[14] = new Option("m","moving-speed", true, "이동속도");
        optionArray[15] = new Option("as","attack-speed", true, "공격속도");
        optionArray[16]= new Option("L","history", false, "변경 이력 보기");
        optionArray[17] = new Option("f","db-file", true, "데이터 저장 파일");

        for(Option option : optionArray){
            options.addOption(option);
        }
    }
}