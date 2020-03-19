package com.ric.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ric.web.rest.TestUtil;

public class LigneOperationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LigneOperation.class);
        LigneOperation ligneOperation1 = new LigneOperation();
        ligneOperation1.setId(1L);
        LigneOperation ligneOperation2 = new LigneOperation();
        ligneOperation2.setId(ligneOperation1.getId());
        assertThat(ligneOperation1).isEqualTo(ligneOperation2);
        ligneOperation2.setId(2L);
        assertThat(ligneOperation1).isNotEqualTo(ligneOperation2);
        ligneOperation1.setId(null);
        assertThat(ligneOperation1).isNotEqualTo(ligneOperation2);
    }
}
