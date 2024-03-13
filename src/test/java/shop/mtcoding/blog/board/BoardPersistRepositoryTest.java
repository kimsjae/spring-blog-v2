package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BoardRepository.class)
public class BoardPersistRepositoryTest {

    @Autowired // IoC에 있는 것을 D.I 해줌
    private BoardRepository boardRepository;

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }


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