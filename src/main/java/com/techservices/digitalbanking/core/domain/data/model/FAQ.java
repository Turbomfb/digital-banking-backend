package com.techservices.digitalbanking.core.domain.data.model;

import com.techservices.digitalbanking.core.domain.enums.ProductType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "faq")
@Setter
@Getter
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(name = "product", nullable = false)
    private ProductType product;

    @Column(name = "del_flg", nullable = false)
    private Boolean delFlg = false;

    @Column(name = "web_enable", nullable = false)
    private Boolean webEnable = false;

    @Column(name = "mobile_enable", nullable = false)
    private Boolean mobileEnable = false;

    @Column(name = "launch_date")
    private LocalDate launchDate;

    // Constructors
    public FAQ() {}

    public FAQ(String question, String answer, ProductType product) {
        this.question = question;
        this.answer = answer;
        this.product = product;
    }

    @Override
    public String toString() {
        return "FAQ{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", product=" + product +
                ", delFlg=" + delFlg +
                ", webEnable=" + webEnable +
                ", mobileEnable=" + mobileEnable +
                ", launchDate=" + launchDate +
                '}';
    }
}
