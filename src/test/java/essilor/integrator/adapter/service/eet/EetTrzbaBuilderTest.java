package essilor.integrator.adapter.service.eet;

import cz.mfcr.fs.eet.schema.v3.Trzba;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class EetTrzbaBuilderTest {

    @Test
    public void testBuilder() {
        Trzba trzba = new EetTrzbaBuilder().new Builder()
                .build();

        Assert.assertThat("uuidzpravy is null", trzba.getHlavicka().getUuidZpravy(), Matchers.notNullValue());
        Assert.assertThat("wrong format uuid zpravy", trzba.getHlavicka().getUuidZpravy(), Matchers.matchesPattern("^([0-9a-fA-F]{8}-){4}[0-9a-fA-F]{8}$"));
        System.out.println(trzba.getHlavicka().getUuidZpravy());
    }
}
