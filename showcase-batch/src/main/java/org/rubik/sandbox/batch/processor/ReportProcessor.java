package org.rubik.sandbox.batch.processor;

import java.util.Date;

import org.rubik.sandbox.batch.domain.DataReport;
import org.rubik.sandbox.batch.domain.Report;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * 中间业务逻辑处理。
 * @author xiajinxin
 *
 */
@Component
public class ReportProcessor implements ItemProcessor<Report, DataReport> {

	@Override
	public DataReport process(Report item) throws Exception {
		DataReport dataReport = new DataReport();
		dataReport.setId(item.getId());
		dataReport.setImpression(item.getImpression());
		dataReport.setEarning(item.getEarning());
		dataReport.setClicks(item.getClicks());
		dataReport.setReportDate(item.getDate());
		dataReport.setRecordTime(new Date());
		dataReport.setRecordUser("xiajinxin");
		return dataReport;
	}

}
