package br.org.cremesp.beneficio.flex.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.cremesp.beneficio.flex.common.DataUtils;

@RunWith(SpringRunner.class)
public class DataUtilsTest {
	
    @Test
    public void whenNewInvallidDateFormat_thenShouldRetunrNull() {
		assertThat(DataUtils.newDateWithFormat("12345678")).isEqualTo(null);
    }

}
