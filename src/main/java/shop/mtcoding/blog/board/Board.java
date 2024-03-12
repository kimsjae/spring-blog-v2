package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "board_tb")
@Getter // setter는 변경돼야 할 컬럼에만 거는 것.
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

//    public void update(String title, String content) { // 이렇게 하는 게 의미있는 setter
//        this.title = title;
//        this.content = content;
//    }
}
