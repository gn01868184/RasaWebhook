package ntou.cs.springboot.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import ntou.cs.springboot.entity.Pharmacy;

public class MaskHandlerTest {
	public static void main(String[] args) {
		try {
			MaskHandler handler = new MaskHandler();
			handler.initialize();
			List<Pharmacy> filteredClinicList = handler.findPharmacies("衛生所", "基隆市中正區");
			System.out.println(filteredClinicList);
			List<Pharmacy> filteredClinicList2 = handler.findPharmacies("百福新豐活力", "基隆市中正區");
			System.out.println(filteredClinicList2);
			List<Pharmacy> filteredClinicList3 = handler.findPharmacies("", "台北市信義區");
			System.out.println(filteredClinicList3);
			Pharmacy filteredClinicList4 = handler.getPharmacy("5911011334");
			System.out.println(filteredClinicList4);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
}
