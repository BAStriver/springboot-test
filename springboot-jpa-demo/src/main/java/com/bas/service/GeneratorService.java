package com.bas.service;

import com.bas.entity.DevopsLogs;
import com.bas.repository.DevopsLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class GeneratorService {

    @Autowired
    private DevopsLogsRepository devopsLogsRepository;

//    部署ID	部署开始时间	部署结束时间	部署状态	所属服务	（所属团队）	是否回滚
//    同一团队每个部署开始时间-部署结束时间的平均值=变更准备时间

    // metadata
    HashMap<Integer, String> statusNameMap = new HashMap<>();
    HashMap<Integer, String> serviceNameMap = new HashMap<>();
    HashMap<Integer, String> teamNameMap = new HashMap<>();
    HashMap<Integer, String> rollbackMap = new HashMap<>();
    HashMap<Integer, Long> startDateMap = new HashMap<>(); // String: seconds

    HashMap<Integer, Integer> testDeployMap = new HashMap<>();
    HashMap<Integer, Integer> prodDeployMap = new HashMap<>();
    HashMap<Integer, Integer> rollbackDeployMap = new HashMap<>();

    HashMap<Integer, Integer> dateMap = new HashMap<>();

    @PostConstruct
    private void init() {
//        devopsLogsRepository.deleteAll();

        teamNameMap.put(1, "GWBD");
        teamNameMap.put(2, "GWBP");
        teamNameMap.put(3, "GWBI");
        teamNameMap.put(4, "GWB4");
        teamNameMap.put(5, "GWB5");

        statusNameMap.put(1, "done");
        statusNameMap.put(2, "SIT deployed success");
        statusNameMap.put(3, "UAT deployed success");
        statusNameMap.put(4, "PROD deployed success");

        serviceNameMap.put(1, "ms-accounts");
        serviceNameMap.put(2, "ms-opening");
        serviceNameMap.put(3, "ms-corp");
        serviceNameMap.put(4, "ms-portfolio");
        serviceNameMap.put(5, "ms-entitlement");
        serviceNameMap.put(6, "ms-customer");
        serviceNameMap.put(7, "ms-profile");
        serviceNameMap.put(8, "ms-dda");
        serviceNameMap.put(9, "ms-fund");
        serviceNameMap.put(10, "ms-int");

        rollbackMap.put(1, "rolled back");
        rollbackMap.put(2, "no rolled back");

        testDeployMap.put(1, 5621); // 2022/1/1
        testDeployMap.put(2, 5132); // 2022/2/1
        testDeployMap.put(3, 5890); // 2022/3/1
        testDeployMap.put(4, 6298); // 2022/4/1
        testDeployMap.put(5, 5981); // 2022/5/1
        testDeployMap.put(6, 6417); // 2022/6/1
        testDeployMap.put(7, 6390); // 2022/7/1
        testDeployMap.put(8, 6011); // 2022/8/1
        testDeployMap.put(9, 6007); // 2022/9/1
        testDeployMap.put(10, 5411); // 2022/10/1
        testDeployMap.put(11, 6319); // 2022/11/1
        testDeployMap.put(12, 6692); // 2022/12/1
        testDeployMap.put(13, 5591); // 2023/1/1

        prodDeployMap.put(1, 149); // 2022/1/1
        prodDeployMap.put(2, 104); // 2022/2/1
        prodDeployMap.put(3, 155); // 2022/3/1
        prodDeployMap.put(4, 180); // 2022/4/1
        prodDeployMap.put(5, 161); // 2022/5/1
        prodDeployMap.put(6, 176); // 2022/6/1
        prodDeployMap.put(7, 168); // 2022/7/1
        prodDeployMap.put(8, 164); // 2022/8/1
        prodDeployMap.put(9, 159); // 2022/9/1
        prodDeployMap.put(10, 143); // 2022/10/1
        prodDeployMap.put(11, 175); // 2022/11/1
        prodDeployMap.put(12, 193); // 2022/12/1
        prodDeployMap.put(13, 157); // 2023/1/1

        rollbackDeployMap.put(1, 4); // 2022/1/1
        rollbackDeployMap.put(2, 2); // 2022/2/1
        rollbackDeployMap.put(3, 2); // 2022/3/1
        rollbackDeployMap.put(4, 16); // 2022/4/1
        rollbackDeployMap.put(5, 14); // 2022/5/1
        rollbackDeployMap.put(6, 14); // 2022/6/1
        rollbackDeployMap.put(7, 12); // 2022/7/1
        rollbackDeployMap.put(8, 8); // 2022/8/1
        rollbackDeployMap.put(9, 10); // 2022/9/1
        rollbackDeployMap.put(10, 6); // 2022/10/1
        rollbackDeployMap.put(11, 10); // 2022/11/1
        rollbackDeployMap.put(12, 22); // 2022/12/1
        rollbackDeployMap.put(13, 8); // 2023/1/1

        startDateMap.put(1, 1640999101L); // 2022/1/1
        startDateMap.put(2, 1643677572L); // 2022/2/1
        startDateMap.put(3, 1646096772L); // 2022/3/1
        startDateMap.put(4, 1648775172L); // 2022/4/1
        startDateMap.put(5, 1651367172L); // 2022/5/1
        startDateMap.put(6, 1654045572L); // 2022/6/1
        startDateMap.put(7, 1656637572L); // 2022/7/1
        startDateMap.put(8, 1659315972L); // 2022/8/1
        startDateMap.put(9, 1661994372L); // 2022/9/1
        startDateMap.put(10, 1664586372L); // 2022/10/1
        startDateMap.put(11, 1667264772L); // 2022/11/1
        startDateMap.put(12, 1669856772L); // 2022/12/1
        startDateMap.put(13, 1672535172L); // 2023/1/1

        dateMap.put(1, 31); // 2022/1/1
        dateMap.put(2, 28); // 2022/2/1
        dateMap.put(3, 31); // 2022/3/1
        dateMap.put(4, 30); // 2022/4/1
        dateMap.put(5, 31); // 2022/5/1
        dateMap.put(6, 30); // 2022/6/1
        dateMap.put(7, 31); // 2022/7/1
        dateMap.put(8, 31); // 2022/8/1
        dateMap.put(9, 30); // 2022/9/1
        dateMap.put(10, 31); // 2022/10/1
        dateMap.put(11, 30); // 2022/11/1
        dateMap.put(12, 31); // 2022/12/1
        dateMap.put(13, 31); // 2023/1/1
    }

    public void generateData() {
        Random rand = new Random();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        int testTotalCount = 77760;
        int prodTotalCount = 2084;
        int rollbackTotalCount = 128;

        for (int month = 0; month < 13; month++) {
            int testCount = testDeployMap.get(month + 1);
            int prodCount = prodDeployMap.get(month + 1);
            int rollbackCount = rollbackDeployMap.get(month + 1);
            long startDate = startDateMap.get(month + 1);

            int currentProdCount = 0;

            for (int t = 0; t < testCount; t++) {
                // test env
                String status = statusNameMap.get(getRandomNumber(rand, 3));
                String service = serviceNameMap.get(getRandomNumber(rand, 10));
                String team = teamNameMap.get(getRandomNumber(rand, 5));
                String deployId = team + "-" + getRandomNumber(rand, 10000);
                String rollback = rollbackMap.get(2);
                int dayGap = getRandomNumber(rand, dateMap.get(month+1));
                int startSecondGap = getRandomNumber(rand, 43200); // 09:00-21:00
                int endSecondGap = (getRandomNumber(rand, 60) + getRandomNumber(rand, 5)) * 60 + getRandomNumber(rand, 60); // 基于startSecondGap后的5-60分钟 + 0-60随机秒
//                System.out.println(t + "--test env startSecondGap--> " + startSecondGap);
//                System.out.println(t + "--test env endSecondGap--> " + endSecondGap);
                Instant deployStartInstant = Instant.ofEpochSecond(startDate).plus(Duration.ofDays(dayGap));
                long deployStartDate = deployStartInstant.plus(Duration.ofSeconds(startSecondGap)).toEpochMilli();
                long deployEndDate = deployStartInstant.plus(Duration.ofSeconds(startSecondGap)).plus(Duration.ofSeconds(endSecondGap)).toEpochMilli();

                DevopsLogs data = DevopsLogs.builder().status(status)
                        .service(service)
                        .team(team)
                        .rollback(rollback)
//                        .startDate(simpleDateFormat.format(new Date(deployStartDate)))
//                        .endDate(simpleDateFormat.format(new Date(deployEndDate)))
                        .startDate(new Date(deployStartDate))
                        .endDate(new Date(deployEndDate))
                        .deployId(deployId).build();

                devopsLogsRepository.save(data);
                System.out.println(t + "--test env--> " + data);

                // prod env
                if (currentProdCount < prodCount && getRandomNumber(rand, 10000) % 3 == 0) {
                    status = statusNameMap.get(4);
                    service = serviceNameMap.get(getRandomNumber(rand, 10));
                    team = teamNameMap.get(getRandomNumber(rand, 5));
//                    deployId = team + "-"+ getRandomNumber(rand,10000);
                    rollback = (rollbackCount == 0) ? rollback : rollbackMap.get(getRandomNumber(rand, 2));
                    startSecondGap = 13 + getRandomNumber(rand, 3); // 22:00-01:00
                    endSecondGap = (getRandomNumber(rand, 10) + getRandomNumber(rand, 5)) * 60 + getRandomNumber(rand, 60); // 基于startSecondGap后的5-10分钟 + 0-60随机秒
//                    System.out.println(t + "--prod env startSecondGap--> " + startSecondGap);
//                    System.out.println(t + "--prod env endSecondGap--> " + endSecondGap);
                    deployStartDate = deployStartInstant.plus(Duration.ofHours(startSecondGap)).toEpochMilli();
                    deployEndDate = deployStartInstant.plus(Duration.ofHours(startSecondGap)).plus(Duration.ofSeconds(endSecondGap)).toEpochMilli();

                    data = DevopsLogs.builder().status(status)
                            .service(service)
                            .team(team)
                            .rollback(rollback)
//                            .startDate(simpleDateFormat.format(new Date(deployStartDate)))
//                            .endDate(simpleDateFormat.format(new Date(deployEndDate)))
                            .startDate(new Date(deployStartDate))
                            .endDate(new Date(deployEndDate))
                            .deployId(deployId).build();

                    devopsLogsRepository.save(data);
                    System.out.println(++currentProdCount + "--prod env--> " + data);
                }

                if(rollback.equals(rollbackMap.get(1))){
                    rollbackCount--;
                }
            }

            // save to DB
            devopsLogsRepository.flush();
        }
    }

    private int getRandomNumber(Random rand, int bound) {
        return rand.nextInt(bound) + 1;
    }

    public static void main(String[] args) throws ParseException {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(rand.nextInt(5) + 1);
        }

        // timestamp
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = simpleDateFormat.parse("2022-01-01"); // 1640966400000
        System.out.println(time.getTime());

        List<String> timeList = new ArrayList<>();
        timeList.add("2022-01-01 09:05:01");
        timeList.add("2022-02-01 09:06:12");
        timeList.add("2022-03-01 09:06:12");
        timeList.add("2022-04-01 09:06:12");
        timeList.add("2022-05-01 09:06:12");
        timeList.add("2022-06-01 09:06:12");
        timeList.add("2022-07-01 09:06:12");
        timeList.add("2022-08-01 09:06:12");
        timeList.add("2022-09-01 09:06:12");
        timeList.add("2022-10-01 09:06:12");
        timeList.add("2022-11-01 09:06:12");
        timeList.add("2022-12-01 09:06:12");
        timeList.add("2023-01-01 09:06:12");
        timeList.add("2023-02-27 11:28:12");

        timeList.forEach(times -> {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = simpleDateFormat2.parse(times);
                System.out.println(times + ": " + date.getTime() + ", " + Instant.ofEpochMilli(date.getTime()).plus(Duration.ofHours(8)));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        // date
        Instant inst1 = Instant.now();
        System.out.println("当前的时间：" + inst1 + ", mills: " + inst1.toEpochMilli() + ", second: " + inst1.getEpochSecond());
        Instant inst3 = inst1.plus(Duration.ofDays(10));       //当前时间+days天后的时间
        System.out.println("当前时间+" + 10 + "天后的时间：" + inst3 + ", mills: " + inst3.toEpochMilli() + ", second: " + inst3.getEpochSecond());

        System.out.println("以毫秒计的时间差：" + Duration.between(inst1, inst3).toMillis());

        System.out.println("当前的时间：" + Instant.ofEpochSecond(1677468332));
        System.out.println("当前时间+10天后的时间：" + Instant.ofEpochSecond(1678332332));

        System.out.println("当前时间+5分钟后的时间：" + Instant.ofEpochSecond(1677468332).plus(Duration.ofSeconds(300)));

    }

}
