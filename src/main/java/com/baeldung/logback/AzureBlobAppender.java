package com.baeldung.logback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.filter.Filter;

import com.microsoft.azure.storage.StorageException;
import com.baeldung.logback.AzureBlobManager;

public class AzureBlobAppender extends AppenderBase<ILoggingEvent> {

	private String storageAccount;
	private String accountKey;
	private String container;
	private String endpointSuffix;

	private AzureBlobManager manager;

	// public AzureBlobAppender() {
	// super(true);
	// }

	private void ensureManager() throws InvalidKeyException, URISyntaxException, StorageException {
		if (manager == null) {
			manager = new AzureBlobManager(getName(), getStorageAccount(), getAccountKey(), getContainer(),
					getEndpointSuffix());
		}
	}

	protected AzureBlobAppender(String name, Filter filter, PatternLayout layout, String storageAccount,
			String accountKey, String container, String endpointSuffix)
			throws InvalidKeyException, URISyntaxException, StorageException {
		super();
		manager = new AzureBlobManager(name, storageAccount, accountKey, container, endpointSuffix);
		// super.setLayout(layout);
		super.setName(name);
		if (filter != null)
			super.addFilter(filter);
	}

	// @Override
	// public void close() {
	// }

	// @Override
	// public boolean requiresLayout() {
	// return false;
	// }

	@Override
	protected void append(ILoggingEvent event) {
		try {
			this.ensureManager();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (StorageException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// try {
		// manager.write(getLayout().format(event));
		// } catch (URISyntaxException | StorageException | IOException |
		// InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public String getStorageAccount() {
		return storageAccount;
	}

	public void setStorageAccount(String storageAccount) {
		this.storageAccount = storageAccount;
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getEndpointSuffix() {
		return endpointSuffix;
	}

	public void setEndpointSuffix(String endpointSuffix) {
		this.endpointSuffix = endpointSuffix;
	}

}
