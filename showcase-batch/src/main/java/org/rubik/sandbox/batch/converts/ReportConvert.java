package org.rubik.sandbox.batch.converts;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.rubik.sandbox.batch.domain.Report;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Component
public class ReportConvert implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(Report.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		Report report = new Report();

		report.setId(Integer.valueOf(reader.getAttribute("id")));
		while (reader.hasMoreChildren()) {
            reader.moveDown();
            if ("date".equals(reader.getNodeName())) {
            	try {
        			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(reader.getValue());
        			report.setDate(date);
        		} catch (ParseException e) {
        			report.setDate(new Date());
        		}
            } else if ("impression".equals(reader.getNodeName())) {
        		try {
        			NumberFormat format = NumberFormat.getInstance(Locale.US);
        			Number number = format.parse(reader.getValue());
        			report.setImpression(number.longValue());
        		} catch (ParseException e) {
        			report.setImpression(0);
        		}
            } else if ("clicks".equals(reader.getNodeName())) {
            	report.setClicks(Integer.valueOf(reader.getValue()));
            } else if ("earning".equals(reader.getNodeName())) {
            	report.setEarning(new BigDecimal(reader.getValue()));
            }
            reader.moveUp();
		}

		return report;
	}

}
