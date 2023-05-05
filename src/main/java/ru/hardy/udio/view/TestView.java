package ru.hardy.udio.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Route(layout = MainView.class)
@RolesAllowed({"ROLE_ADMIN"})
public class TestView extends VerticalLayout {

    @Autowired
    private PolzService polzService;

    public TestView(){

        Button button = new Button("Test");
        button.addClickListener(ev -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Notification.show(Date.from(Instant.parse(dateFormat.format(Instant.now()))) + " | " + Date.from(Instant.now()));

        });
        add(button);
    }

    @Data
    @Entity
    static class Polz {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;

        @OneToMany(mappedBy = "polz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<Address> adresses;

        public Polz(String name){
            this.name = name;
        }

        public Polz() {

        }
    }

    @Data
    @Entity
    static class Address{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id ;

        private String name;
        @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
        @JoinColumn (name="polz_id")
        private Polz polz;

        public Address(String name, Polz polz){
            this.name = name;
            this.polz = polz;
        }

        public Address() {

        }
    }

    @Service
    static class PolzService{
        @Autowired
        private PolzRepo polzRepo;

        public Polz save(Polz polz){
            return polzRepo.save(polz);
        }
    }

    @Service
    static class AddressService{
        @Autowired
        private AddressRepo addressRepo;

        public Address save(Address address){
            return addressRepo.save(address);
        }
    }
}

@Repository
interface AddressRepo extends JpaRepository<TestView.Address, Long>{

}

@Repository
interface PolzRepo extends JpaRepository<TestView.Polz, Long>{

}

