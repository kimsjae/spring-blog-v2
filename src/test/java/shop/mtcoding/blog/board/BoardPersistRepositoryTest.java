package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(BoardRepository.class)
public class BoardPersistRepositoryTest {

    @Autowired // IoC에 있는 것을 D.I 해줌
    private BoardRepository boardRepository;

    @Test
    public void save_test() {
        // given
        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardRepository.save(board);
        System.out.println("save_test : " + board);

        // then
    }
}