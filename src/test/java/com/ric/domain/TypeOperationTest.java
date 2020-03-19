package com.ric.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ric.web.rest.TestUtil;

public class TypeOperationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeOperation.class);
        TypeOperation typeOperation1 = new TypeOperation();
        typeOperation1.setId(1L);
        TypeOperation typeOperation2 = new TypeOperation();
        typeOperation2.setId(typeOperation1.getId());
        assertThat(typeOperation1).isEqualTo(typeOperation2);
        typeOperation2.setId(2L);
        assertThat(typeOperation1).isNotEqualTo(typeOperation2);
        typeOperation1.setId(null);
        assertThat(typeOperation1).isNotEqualTo(typeOperation2);
    }
}
