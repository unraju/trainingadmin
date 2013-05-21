package webservices.restful;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBMarshellingHelper {

	public static String marshellObject(Object object, Class baseCalss) {

		String result;
		StringWriter sw = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(baseCalss);
			Marshaller repsonseMarshaller = context.createMarshaller();
			repsonseMarshaller.marshal(object, sw);

		} catch (JAXBException e) {
			 throw new RuntimeException(e);
		}
		result = sw.toString();
		return result;
	}
}
