package hillelee.knight;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class FairyTale {
    public static void main(String[] args) {
        // Problem: for create new Knight always need Quest
        //Knight knight = new Knight(new Quest());

        ApplicationContext ctx = new AnnotationConfigApplicationContext("hillelee");

        System.out.println(ctx.getBean("knight"));
        System.out.println(ctx.getBean(Knight.class));
        System.out.println(new Knight(new Quest()));


    }
}

@Data
@Component
class Knight {
    private final Quest quest;
}

@Data
@Component
class Quest {
    private final String task = "Kill the Dragon";
}