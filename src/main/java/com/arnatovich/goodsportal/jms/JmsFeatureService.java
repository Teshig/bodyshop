package com.arnatovich.goodsportal.jms;

import com.arnatovich.goodsportal.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsFeatureService {

  private JmsTemplate jms;
  
  @Autowired
  public JmsFeatureService(JmsTemplate jms) {
    this.jms = jms;
  }
  
  public void sendFeature(Feature feature) {
    jms.send("features.feature2", session -> session.createObjectMessage(feature));
  }
}
