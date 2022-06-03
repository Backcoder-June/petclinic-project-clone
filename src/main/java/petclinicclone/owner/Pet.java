package petclinicclone.owner;

import org.springframework.format.annotation.DateTimeFormat;
import petclinicclone.Model.NamedEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "pets")                         // 강아지용 Table 생성
public class Pet extends NamedEntity {


    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")    // 패턴 정해주기
    private LocalDate birthDate;               //datatype - LocalDate 날짜

                           //category 는 많은 book 을 가짐 -> category 입장에서 one to many
                          // type 은    많은   pet 을 가짐 -> pet 입장에서 type 은 many to one (많은게 나한테 온다)
    @ManyToOne                                // Table - Table 연관관계 매핑
    @JoinColumn(name = "type_id")             // @JoinColumn <- foreign 키 매핑
    private PetType type;                     // "필드명_참조하는테이블(pets)의기본key(@Id)Column명"



    @OneToMany()                    //<cascade 보충>
    @JoinColumn(name = "pet_id")       // <table key 이름짓는거 보충>
    @OrderBy("visit_data ASC")
    private Set<Visit> visits = new LinkedHashSet<>();

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    //  visit 은 add 로 한다. Set 안하고 차이점 <보충>
    public void addVisit(Visit visit) {

        getVisits().add(visit);
    }
}
