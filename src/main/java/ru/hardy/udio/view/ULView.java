package ru.hardy.udio.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.regul.DocumentUL;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.service.reguiservice.FileUlService;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class ULView extends VerticalLayout {

    @Autowired
    private FileUlService fileUlService;

    public ULView(){
        Set<String> files = new HashSet<>();
        MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();
        Upload multiFileUpload = new Upload(multiFileMemoryBuffer);
        multiFileUpload.setAcceptedFileTypes(".xml");
        multiFileUpload.addSucceededListener(succeededEvent -> {

            JAXBContext context = null;
            try {
                InputStreamReader reader = new InputStreamReader(multiFileMemoryBuffer.getInputStream(succeededEvent.getFileName()), "windows-1251");
                context = JAXBContext.newInstance(FileUL.class);
                FileUL unmarshal = (FileUL) context
                        .createUnmarshaller()
                        .unmarshal(reader);

                System.out.println(unmarshal.getQuantityDoc() + " | " + unmarshal.getIdFile() + " | "
                        + unmarshal.getSenderPeople().getPhone());
                for (DocumentUL documentUL: unmarshal.getDocumentUL()){
                    System.out.println(documentUL.getIdDoc());
                    System.out.println(documentUL.getPersonUL().getInn());
                    System.out.println(documentUL.getPersonUL().getNameUl().getFullName());
                    System.out.println(documentUL.getPersonUL().getDateOgrn());
                }
//                System.out.println(unmarshal.getQuantityDoc() + " | " + unmarshal.getIdFile());
//                fileUlService.add(unmarshal);

            } catch (JAXBException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }


//            files.addAll(multiFileMemoryBuffer.getFiles());
//            //String fileName = "C:\\Users\\cherchesov\\YandexDisk\\App\\FifteenChapter\\docs\\ЮЛ\\"+succeededEvent.getFileName();
//
//            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
//            XMLEventReader reader;
//            FileUL fileUL = new FileUL();
//            Set<DocumentUL> documentUL = new HashSet<>();
//
//            try {
//                reader = xmlInputFactory.createXMLEventReader(multiFileMemoryBuffer.getInputStream(succeededEvent.getFileName()));
//                while (reader.hasNext()) {
//                    XMLEvent nextEvent = reader.nextEvent();
//                    if (nextEvent.isStartElement()) {
//                        StartElement startElement = nextEvent.asStartElement();
//                        switch (startElement.getName().getLocalPart()) {
//                            case "Файл":
//                                Attribute idFile = startElement.getAttributeByName(new QName("ИдФайл"));
//                                if (idFile != null) {
//                                    fileUL.setIdFile(idFile.getValue());
//                                    System.out.println(fileUL.getIdFile());
//                                }
//                                Attribute versForm = startElement.getAttributeByName(new QName("ВерсФорм"));
//                                if (versForm != null) {
//                                    fileUL.setFormatVersion(versForm.getValue());
//                                    System.out.println(fileUL.getFormatVersion());
//                                }
//                                Attribute infoType = startElement.getAttributeByName(new QName("ТипИнф"));
//                                if (infoType != null) {
//                                    fileUL.setInfoType(infoType.getValue());
//                                    System.out.println(fileUL.getInfoType());
//                                }
//                                Attribute transmissionProgramVersion = startElement.getAttributeByName(new QName("ВерсПрог"));
//                                if (transmissionProgramVersion != null) {
//                                    fileUL.setTransmissionProgramVersion(transmissionProgramVersion.getValue());
//                                    System.out.println(fileUL.getTransmissionProgramVersion());
//                                }
//                                Attribute quantityDoc = startElement.getAttributeByName(new QName("КолДок"));
//                                if (quantityDoc != null) {
//                                    fileUL.setQuantityDoc(quantityDoc.getValue().trim());
//                                    System.out.println(fileUL.getQuantityDoc());
//                                }
//                                break;
//                            case "ИдОтпр":
//                                Attribute position = startElement.getAttributeByName(new QName("ДолжОтв"));
//                                SenderPeople senderPeople = new SenderPeople();
//                                if (position != null) {
//                                    senderPeople.setPosition(position.getValue());
//                                    System.out.println(senderPeople.getPosition());
//                                }
//                                Attribute phone = startElement.getAttributeByName(new QName("Тлф"));
//                                if (phone != null) {
//                                    senderPeople.setPhone(phone.getValue());
//                                    System.out.println(senderPeople.getPhone());
//                                }
//                                Attribute email = startElement.getAttributeByName(new QName("E-mail"));
//                                if (email != null) {
//                                    senderPeople.setEmail(email.getValue());
//                                    System.out.println(senderPeople.getEmail());
//                                }
//
//
//                                break;
//                            case "ФИООтв":
//
//                        }
//                    }
//
//                }
//
//                String test = "";
//            } catch (XMLStreamException e) {
//                throw new RuntimeException(e);
//            }

        });

        add(multiFileUpload);
    }

}
