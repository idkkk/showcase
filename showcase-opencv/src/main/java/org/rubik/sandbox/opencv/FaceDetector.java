package org.rubik.sandbox.opencv;

import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;

import com.google.common.base.Throwables;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

public class FaceDetector {

	public static final String XML_FILE = "/usr/local/share/OpenCV/haarcascades/haarcascade_frontalface_default.xml";

	public static void main(String[] args) {
		IplImage img = cvLoadImage("face1.jpg");
		detect(img);
	}

	public static void detect(IplImage src) {
		CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(XML_FILE));
		CvMemStorage storage = CvMemStorage.create();
		CvSeq sign = cvHaarDetectObjects(src, cascade, storage, 1.5, 3, CV_HAAR_DO_CANNY_PRUNING);
		cvClearMemStorage(storage);

		int totalFaces = sign.total();
		for (int i = 0; i < totalFaces; i++) {
			try (CvRect r = new CvRect(cvGetSeqElem(sign, i))) {
				cvRectangle(src, cvPoint(r.x(), r.y()), cvPoint(r.width() + r.x(), r.height() + r.y()), CvScalar.RED, 2, CV_AA, 0);
			} catch (Exception e) {
				System.err.println(Throwables.getStackTraceAsString(e));
			}
		}
		cvShowImage("Result", src);
		cvWaitKey(0);
	}
}
