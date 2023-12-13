package ru.hardy.udio.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.InputSource;
import ru.hardy.udio.domain.regul.DocumentUL;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.test.OneParent;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;


@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class IPView extends VerticalLayout {



}

