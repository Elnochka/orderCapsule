package core;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class OrderCapsuleImpl implements OrderCapsule {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCapsuleImpl.class);
    private DataForJson dataForJson = new DataForJson();
    private CapsuleNominalValue capsuleNominalValue = CapsuleNominalValue.getInstance();
    private InputArrays inputArrays = new InputArrays();

    @Override
    public void readJson(String fileJson) {
        try (JsonParser jParser = new JsonFactory().createParser(new File(fileJson))) {
            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                String basename = jParser.getCurrentName();
                if ("corrections".equals(basename)) {
                    if (jParser.nextToken() == JsonToken.START_ARRAY) {
                        while (jParser.nextToken() != JsonToken.END_ARRAY) {
                            inputArrays.getCorrections().add(jParser.getIntValue());
                        }
                    }
                }
                if ("cells".equals(basename)) {
                    if (jParser.nextToken() == JsonToken.START_ARRAY) {
                        while (jParser.nextToken() != JsonToken.END_ARRAY) {
                            inputArrays.getCells().add(jParser.getIntValue());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeJson(String fileJson){

        ObjectMapper mapper = new ObjectMapper();
        try (JsonGenerator jGenerator =
                     mapper.getFactory().createGenerator(
                             new File(fileJson)
                             , JsonEncoding.UTF8)) {

            jGenerator.useDefaultPrettyPrinter();
            jGenerator.writeStartObject();

            jGenerator.writeFieldName("main_thruster");
            jGenerator.writeStartArray();
            for(Integer curSet: dataForJson.getMainThruster()) {
                jGenerator.writeObject(curSet);
            }
            jGenerator.writeEndArray();

            jGenerator.writeFieldName("sec_thruster");
            jGenerator.writeStartArray();
            for(Integer curSet: dataForJson.getSecThruster()) {
                jGenerator.writeObject(curSet);
            }
            jGenerator.writeEndArray();

            jGenerator.writeNumberField("delta_velocity", dataForJson.getDeltaVelocity());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {
        findCapsuleFirst();
        findCapsuleSecond();
        LOGGER.info(dataForJson.getMainThruster().toString());
        LOGGER.info(dataForJson.getSecThruster().toString());
    }

    @Override
    public void findCapsuleSecond() {
        //перебираем приращения скорости
        for(int i = 0; i < inputArrays.getCorrections().size(); i++){
            //если уже нет свободных капсул
            if(inputArrays.getCells().isEmpty()){
                dataForJson.getSecThruster().add(0);
            }
            int maneuver = inputArrays.getCorrections().get(i);
            int restSpeed = maneuver - dataForJson.getMainThruster().get(i);
            if(restSpeed == 0){
                dataForJson.getSecThruster().add(0);
            }else {
                addToSecond(restSpeed);
            }
        }
        //дельта второго двигателя
        Integer deltaSecond = dataForJson.getSecThruster().stream()
                .mapToInt(num -> num / 2)
                .sum();
        int delta = dataForJson.getDeltaVelocity();
        dataForJson.setDeltaVelocity(delta + deltaSecond);
    }

    @Override
    public void addToSecond(int restSpeed){
        //ищем подходящие капсулы для массива2
        for (Integer secNumb : capsuleNominalValue.getCapsuleNominalValue()) {
            if((secNumb / 2) <= restSpeed) {
                if (inputArrays.getCells().contains(secNumb)) {
                    dataForJson.getSecThruster().add(secNumb);
                    inputArrays.getCells().remove(secNumb);
                    break;
                }
            }
        }
    }

    @Override
    public void addToFirst(int maneuver){
        //ищем подходящие капсулы для массива1
        for (Integer secNumb : capsuleNominalValue.getCapsuleNominalValue()) {
            if(secNumb <= maneuver) {
                if (inputArrays.getCells().contains(secNumb)) {
                    dataForJson.getMainThruster().add(secNumb);
                    inputArrays.getCells().remove(secNumb);
                    break;
                }
            }
        }
    }

    @Override
    public void findCapsuleFirst() {
        //перебираем приращения скорости
        for (Integer correction : inputArrays.getCorrections()) {
            int maneuver = correction;
            if(maneuver == 1){
                dataForJson.getMainThruster().add(0);
            } else {
                addToFirst(maneuver);
            }
        }
        //дельта первого двигателя
        Integer deltaFirst = dataForJson.getMainThruster().stream()
                .mapToInt(Integer::intValue)
                .sum();
        dataForJson.setDeltaVelocity(deltaFirst);
    }

}
