package site.lemongproject.web.challenge.model.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChallengeOptionTest {
    @Test
    public void option(){
        String option="11111110";
        ChallengeOption c=ChallengeOption.getInstance(option);
        Assertions.assertThat(c).isNotNull();

//        String o=ChallengeOption.parseOption(c);
//        Assertions.assertThat(o).isEqualTo(option);


    }
}