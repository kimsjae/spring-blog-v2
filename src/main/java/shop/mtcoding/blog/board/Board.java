package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@Entity
@Table(name = "board_tb")
@Data // setter는 변경돼야 할 컬럼에만 거는 것.
@NoArgsConstructor // 기본생성자가 무조건 있어야 한다.
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;

    @CreationTimestamp // PC를 통해 컨텍스트 될 때 자동으로 날짜를 만들어줌. PC -> DB 날짜주입
    private Timestamp createdAt;

    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }

//    public void update(String title, String content) { // 이렇게 하는 게 의미있는 setter
//        this.title = title;
//        this.content = content;
//    }
}
