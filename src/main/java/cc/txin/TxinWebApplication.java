package cc.txin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cc.txin")
@MapperScan({"cc.txin.admin.mapper"})
public class TxinWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxinWebApplication.class, args);
    }
}
