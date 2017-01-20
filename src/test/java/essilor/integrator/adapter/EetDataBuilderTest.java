package essilor.integrator.adapter;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class EetDataBuilderTest {

    @Test
    public void testBuilder() throws  Exception {
        StringBuilder sb = new StringBuilder()
                .append("110")
                .append("A")
                .append("A")
                .append("000001111.11")
                .append("000002222.22")
                .append("000003333.33")
                .append("000004444.44")
                .append("-00005555.55")
                .append("2016-06-12 12:00:56")
                .append("0123456789")
                .append("333")
                .append("22")
                .append("012345678901234567890123456789")
                .append(" ");

        EetData data = EetDataBuilder.newInstance().withRequest(sb.toString()).build();

        Assert.assertThat("wrong mode", data.getMode(), Matchers.equalTo("A"));
        Assert.assertThat("wrong first", data.isFirst(), Matchers.is(true));
        Assert.assertThat("wrong CelkTrzba", data.getCelkTrzba(), Matchers.equalTo(new BigDecimal("000001111.11")));
        Assert.assertThat("wrong ZaklDan1", data.getZaklDan1(), Matchers.equalTo(new BigDecimal("000002222.22")));
        Assert.assertThat("wrong Dan1", data.getDan1(), Matchers.equalTo(new BigDecimal("000003333.33")));
        Assert.assertThat("wrong ZaklDan2", data.getZaklDan2(), Matchers.equalTo(new BigDecimal("000004444.44")));
        Assert.assertThat("wrong Dan2", data.getDan2(), Matchers.equalTo(new BigDecimal("-5555.55")));
        Assert.assertThat("wrong DatTrzby", data.getDatTrzby(), Matchers.equalTo(new SimpleDateFormat(EetDataBuilder.DATE_TIME_MASK).parse("2016-06-12 12:00:56")));
        Assert.assertThat("wrong CisDokladu", data.getPoradoveCisloDokladu(), Matchers.equalTo("0123456789"));
        Assert.assertThat("wrong SkupinaDokladu", data.getSkupinaDokladu(), Matchers.equalTo("333"));
        Assert.assertThat("wrong SkupinaZakazok", data.getSkupinaZakazok(), Matchers.is("22"));
        Assert.assertThat("wrong Datasourcename", data.getDataSourceName(), Matchers.equalTo("012345678901234567890123456789"));
        Assert.assertThat("wrong PoradoveCislo", data.getPoradoveCislo(), Matchers.equalTo("0123456789-333-22"));
    }
}
