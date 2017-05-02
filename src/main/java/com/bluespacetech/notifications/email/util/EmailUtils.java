package com.bluespacetech.notifications.email.util;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

public class EmailUtils {

	public final static String EMAIL_SECRET_KEY = "ThisIsKeyForEmailEncryptDecrypt";

	public static String generateUnscribeLink(final EmailContactGroupVO emailContactGroupVO,
			final String emailRequestURL) {
		final StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/unsubscribe");
		unscribeLink.append("?contactEmail=").append(emailContactGroupVO.getContactEmail()).append("&contactId=")
		.append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
		.append(emailContactGroupVO.getGroupId().toString());
		/*
		 * try { final CryptoUtil cryptoUtil = new CryptoUtil(); try {
		 * unscribeLink.append("?contactEmail=").append(emailContactGroupVO.
		 * getContactEmail()) .append("&contactId=") .append(URLEncoder.encode(
		 * cryptoUtil.encrypt(EMAIL_SECRET_KEY,
		 * emailContactGroupVO.getContactId().toString()), "UTF-8"))
		 * .append("&groupId=") .append(URLEncoder.encode(
		 * cryptoUtil.encrypt(EMAIL_SECRET_KEY,
		 * emailContactGroupVO.getGroupId().toString()), "UTF-8"));
		 * 
		 * } catch (InvalidKeyException | InvalidKeySpecException |
		 * NoSuchPaddingException | InvalidAlgorithmParameterException |
		 * UnsupportedEncodingException | IllegalBlockSizeException |
		 * BadPaddingException e) { e.printStackTrace(); } } catch (final
		 * NoSuchAlgorithmException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return unscribeLink.toString();
	}

	public static String generateReadMailImageSRC(final EmailContactGroupVO emailContactGroupVO,
			final String emailRequestURL, final Long emailRandomNumber) {
		final StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/readMail");
		unscribeLink.append("?emailRandomNumber=").append(emailRandomNumber).append("&contactId=")
		.append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
		.append(emailContactGroupVO.getGroupId().toString());
		/*
		 * try { final CryptoUtil cryptoUtil = new CryptoUtil(); try {
		 * unscribeLink.append("?emailRandomNumber=").append(emailRandomNumber)
		 * .append("&contactId=") .append(URLEncoder.encode(
		 * cryptoUtil.encrypt(EMAIL_SECRET_KEY,
		 * emailContactGroupVO.getContactId().toString()), "UTF-8"))
		 * .append("&groupId=") .append(URLEncoder.encode(
		 * cryptoUtil.encrypt(EMAIL_SECRET_KEY,
		 * emailContactGroupVO.getGroupId().toString()), "UTF-8"));
		 * 
		 * } catch (InvalidKeyException | InvalidKeySpecException |
		 * NoSuchPaddingException | InvalidAlgorithmParameterException |
		 * UnsupportedEncodingException | IllegalBlockSizeException |
		 * BadPaddingException e) { e.printStackTrace(); } } catch (final
		 * NoSuchAlgorithmException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return unscribeLink.toString();
	}
}
