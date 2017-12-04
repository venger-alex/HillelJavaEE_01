package hillelee.knight;

        import lombok.Data;
        import org.springframework.beans.factory.config.ConfigurableBeanFactory;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;
        import org.springframework.context.annotation.Scope;
        import org.springframework.stereotype.Component;

public class FairyTale {
    public static void main(String[] args) {
        // Problem: for create new Knight always need Quest
        //Knight knight = new Knight(new Quest());

        ApplicationContext ctx = new AnnotationConfigApplicationContext("hillelee");

//        System.out.println(ctx.getBean("knight"));
//        System.out.println(ctx.getBean(Knight.class));
//        System.out.println(new Knight(new Quest()));
//        System.out.println(ctx.getBean("myKnight"));
        Knight knight1 = ctx.getBean(Knight.class);
        Knight knight2 = ctx.getBean(Knight.class);
        System.out.println("Knights are same: " + (knight1 == knight2));
        System.out.println("Quests are same: " + (knight1.getQuest() == knight2.getQuest()));

    }
}

@Data
@Component("myKnight")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class Knight {
    private final Quest quest;
}

@Data
@Component
class Quest {
    private final String task = "Kill the Dragon";
}