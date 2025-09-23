package com.minhadose.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampaignsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campaignId;
    private String campaignName;
    private String campaignDescription;

    @Lob
    private byte[] campaignCard;

    @OneToOne
    private UbsModel ubs;

}