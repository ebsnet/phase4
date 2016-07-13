/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.as4lib.model.pmode;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.state.ETriState;

/**
 * Error handling - This P-Mode group concerns errors generated by the reception
 * of the message (for either a User message or a Signal message, unless
 * indicated otherwise) sent over leg 1 of the MEP.
 *
 * @author Philip Helger
 */
public class PModeLegErrorHandling
{
  public static final boolean DEFAULT_REPORT_AS_RESPONSE = false;
  public static final boolean DEFAULT_REPORT_PROCESS_ERROR_NOTIFY_CONSUMER = false;
  public static final boolean DEFAULT_REPORT_PROCESS_ERROR_NOTIFY_PRDOUCER = false;
  public static final boolean DEFAULT_REPORT_DELIVERY_FAILURES_NOTIFY_PRODUCER = false;
  /**
   * This parameter indicates the address, or comma-separated list of addresses,
   * to which to send ebMS errors generated by the MSH that was trying to send
   * the message in error.
   */
  private PModeAddressList m_aReportSenderErrorsTo;

  /**
   * This parameter indicates the address, or comma-separated list of addresses,
   * to which to send ebMS errors generated by the MSH that receives the message
   * in error; e.g. this may be the address of the MSH sending the message in
   * error.
   */
  private PModeAddressList m_aReportReceiverErrorsTo;

  /**
   * This Boolean parameter indicates whether (if "true") errors generated from
   * receiving a message in error are sent over the back-channel of the
   * underlying protocol associated with the message in error, or not.
   */
  private ETriState m_eReportAsResponse;

  /**
   * This Boolean parameter indicates whether (if "true") the Consumer
   * (application/party) of a User Message matching this P-Mode should be
   * notified when an error occurs in the Receiving MSH, during processing of
   * the received User message.
   */
  private ETriState m_eReportProcessErrorNotifyConsumer;
  /**
   * This Boolean parameter indicates whether (if "true") the Producer
   * (application/party) of a User Message matching this P-Mode should be
   * notified when an error occurs in the Sending MSH, during processing of the
   * User Message to be sent.
   */
  private ETriState m_eReportProcessErrorNotifyProducer;

  /**
   * This Boolean parameter indicates whether (if "true") the Producer
   * (application/party) of a User Message matching this P-Mode must always be
   * notified when the delivery to Consumer failed, or whether (if "false"), in
   * some cases, it is sufficient to notify the Consumer only
   * (Report.ProcessErrorNotifyConsumer="true"). This assumes that
   * Reliability.AtLeastOnce.Contract is "true". This also assumes that the
   * Sending MSH implementation has the ability to determine or to be made aware
   * of all cases of non-delivery that occur after the message has been received
   * by the Receiving MSH.
   */
  private ETriState m_eReportDeliveryFailuresNotifyProducer;

  public PModeLegErrorHandling (final PModeAddressList aReportSenderErrorsTo,
                                final PModeAddressList aReportReceiverErrorsTo,
                                final ETriState eReportAsResponse,
                                final ETriState eReportProcessErrorNotifyConsumer,
                                final ETriState eReportProcessErrorNotifyProducer,
                                final ETriState eReportDeliveryFailuresNotifyProducer)
  {
    m_aReportSenderErrorsTo = aReportSenderErrorsTo;
    m_aReportReceiverErrorsTo = aReportReceiverErrorsTo;
    m_eReportAsResponse = eReportAsResponse;
    m_eReportProcessErrorNotifyConsumer = eReportProcessErrorNotifyConsumer;
    m_eReportProcessErrorNotifyProducer = eReportProcessErrorNotifyProducer;
    m_eReportDeliveryFailuresNotifyProducer = eReportDeliveryFailuresNotifyProducer;
  }

  public PModeLegErrorHandling ()
  {}

  public PModeAddressList getReportSenderErrorsTo ()
  {
    return m_aReportSenderErrorsTo;
  }

  public void setReportSenderErrorsTo (final PModeAddressList aReportSenderErrorsTo)
  {
    m_aReportSenderErrorsTo = aReportSenderErrorsTo;
  }

  public PModeAddressList getReportReceiverErrorsTo ()
  {
    return m_aReportReceiverErrorsTo;
  }

  public void setReportReceiverErrorsTo (final PModeAddressList aReportReceiverErrorsTo)
  {
    m_aReportReceiverErrorsTo = aReportReceiverErrorsTo;
  }

  public boolean isReportAsResponseDefined ()
  {
    return m_eReportAsResponse.isDefined ();
  }

  @Nonnull
  public boolean isReportAsResponse ()
  {
    return m_eReportAsResponse.getAsBooleanValue (DEFAULT_REPORT_AS_RESPONSE);
  }

  public void setReportAsResponse (final boolean eReportAsResponse)
  {
    setReportAsResponse (ETriState.valueOf (eReportAsResponse));
  }

  public void setReportAsResponse (final ETriState eReportAsResponse)
  {
    ValueEnforcer.notNull (eReportAsResponse, "ReportAsResponse");
    m_eReportAsResponse = eReportAsResponse;
  }

  public boolean isReportProcessErrorNotifyConsumerDefined ()
  {
    return m_eReportProcessErrorNotifyConsumer.isDefined ();
  }

  @Nonnull
  public boolean isReportProcessErrorNotifyConsumer ()
  {
    return m_eReportProcessErrorNotifyConsumer.getAsBooleanValue (DEFAULT_REPORT_PROCESS_ERROR_NOTIFY_CONSUMER);
  }

  public void setReportProcessErrorNotifyConsumer (final boolean eReportProcessErrorNotifyConsumer)
  {
    setReportProcessErrorNotifyConsumer (ETriState.valueOf (eReportProcessErrorNotifyConsumer));
  }

  public void setReportProcessErrorNotifyConsumer (final ETriState eReportProcessErrorNotifyConsumer)
  {
    ValueEnforcer.notNull (eReportProcessErrorNotifyConsumer, "ReportProcessErrorNotifyConsumer");
    m_eReportProcessErrorNotifyConsumer = eReportProcessErrorNotifyConsumer;
  }

  public boolean isReportProcessErrorNotifyProducerDefined ()
  {
    return m_eReportProcessErrorNotifyProducer.isDefined ();
  }

  @Nonnull
  public boolean isReportProcessErrorNotifyProducer ()
  {
    return m_eReportProcessErrorNotifyProducer.getAsBooleanValue (DEFAULT_REPORT_PROCESS_ERROR_NOTIFY_PRDOUCER);
  }

  public void setReportProcessErrorNotifyProducer (final boolean eReportProcessErrorNotifyProducer)
  {
    setReportProcessErrorNotifyProducer (ETriState.valueOf (eReportProcessErrorNotifyProducer));
  }

  public void setReportProcessErrorNotifyProducer (final ETriState eReportProcessErrorNotifyProducer)
  {
    ValueEnforcer.notNull (eReportProcessErrorNotifyProducer, "ReportProcessErrorNotifyProducer");
    m_eReportProcessErrorNotifyProducer = eReportProcessErrorNotifyProducer;
  }

  public boolean isReportDeliveryFailuresNotifyProducerDefined ()
  {
    return m_eReportDeliveryFailuresNotifyProducer.isDefined ();
  }

  @Nonnull
  public boolean isReportDeliveryFailuresNotifyProducer ()
  {
    return m_eReportDeliveryFailuresNotifyProducer.getAsBooleanValue (DEFAULT_REPORT_DELIVERY_FAILURES_NOTIFY_PRODUCER);
  }

  public void setReportDeliveryFailuresNotifyProducer (final boolean eReportDeliveryFailuresNotifyProducer)
  {
    setReportDeliveryFailuresNotifyProducer (ETriState.valueOf (eReportDeliveryFailuresNotifyProducer));
  }

  public void setReportDeliveryFailuresNotifyProducer (final ETriState eReportDeliveryFailuresNotifyProducer)
  {
    ValueEnforcer.notNull (eReportDeliveryFailuresNotifyProducer, "ReportDeliveryFailuresNotifyProducer");
    m_eReportDeliveryFailuresNotifyProducer = eReportDeliveryFailuresNotifyProducer;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PModeLegErrorHandling rhs = (PModeLegErrorHandling) o;
    return m_aReportReceiverErrorsTo.equals (rhs.m_aReportReceiverErrorsTo) &&
           EqualsHelper.equals (m_aReportSenderErrorsTo, rhs.m_aReportSenderErrorsTo) &&
           EqualsHelper.equals (m_eReportAsResponse, rhs.m_eReportAsResponse) &&
           EqualsHelper.equals (m_eReportDeliveryFailuresNotifyProducer, rhs.m_eReportDeliveryFailuresNotifyProducer) &&
           EqualsHelper.equals (m_eReportProcessErrorNotifyConsumer, rhs.m_eReportProcessErrorNotifyConsumer) &&
           EqualsHelper.equals (m_eReportProcessErrorNotifyProducer, rhs.m_eReportProcessErrorNotifyProducer);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aReportReceiverErrorsTo)
                                       .append (m_aReportSenderErrorsTo)
                                       .append (m_eReportAsResponse)
                                       .append (m_eReportDeliveryFailuresNotifyProducer)
                                       .append (m_eReportProcessErrorNotifyConsumer)
                                       .append (m_eReportProcessErrorNotifyProducer)
                                       .getHashCode ();
  }
}
