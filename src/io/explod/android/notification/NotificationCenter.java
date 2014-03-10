package io.explod.android.notification;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.explod.android.collections.weak.WeakArrayList;
import io.explod.android.collections.weak.WeakList;

/**
 * Notification Center implementation
 * <p/>
 * Allows listeners to subscribe to, and un-subscribe from, notifications
 * identified by a String name
 */
public class NotificationCenter {

	// inner

	/**
	 * Class representing a notification to broadcast
	 */
	public static class Notification {

		/**
		 * Name of the {@link Notification}
		 */
		private String notificationName;

		/**
		 * Construct this {@link Notification} with a name
		 * 
		 * @param notificationName
		 *            Name of the {@link Notification}, subscribers subscribed
		 *            to the notification by this name will be notified
		 */
		public Notification(String notificationName) {
			this.notificationName = notificationName;
		}

		/**
		 * @return Name of this {@link Notification}
		 */
		public String getNotificationName() {
			return this.notificationName;
		}

	}

	/**
	 * Subscriber interface
	 */
	public static interface INotificationListener {
		/**
		 * Event triggered when a notification is broadcasted
		 * 
		 * @param notificationCenter
		 *            The {@link NotificationCenter} sending the broadcast
		 * @param notification
		 *            The actual {@link Notification} being broadcasted
		 */
		public void onNotification(NotificationCenter notificationCenter, Notification notification);
	}

	// static

	/**
	 * Default {@link NotificationCenter} shared instance
	 */
	private static NotificationCenter defaultInstance;

	/**
	 * Singleton method for the default {@link NotificationCenter}
	 * 
	 * @return The shared instance of {@link NotificationCenter}
	 */
	public static synchronized final NotificationCenter getDefaultInstance() {
		if (defaultInstance == null) {
			defaultInstance = new NotificationCenter();
		}
		return defaultInstance;
	}

	/**
	 * Construct a new {@link NotificationCenter} Consider using
	 * {@link NotificationCenter#getDefaultInstance()}
	 */
	public NotificationCenter() {
		this.subscribers = new HashMap<String, WeakList<INotificationListener>>();
	}

	/**
	 * Subscriber storage
	 */
	private Map<String, WeakList<INotificationListener>> subscribers;

	/**
	 * Broadcast a {@link Notification} that is created only with a name
	 * 
	 * @param notificationName
	 *            The name of the {@link Notification} to broadcast
	 */
	public void broadcast(String notificationName) {
		final Notification notification = new Notification(notificationName);
		this.broadcast(notification);
	}

	/**
	 * Broadcast a {@link Notification}
	 * 
	 * @param notification
	 *            The {@link Notification} / Notification-subclass to broadcast
	 */
	public void broadcast(Notification notification) {
		final String notificationName = notification.getNotificationName();
		final WeakList<INotificationListener> listeners = this.subscribers.get(notificationName);
		if (listeners != null) {
			final Iterator<INotificationListener> it = listeners.iterator();
			while (it.hasNext()) {
				final INotificationListener listener = it.next();
				listener.onNotification(this, notification);
			}
		}
	}

	/**
	 * Subscribe to a {@link Notification}
	 * 
	 * @param notificationName
	 *            The name of the {@link Notification} to subscribe to
	 * @param listener
	 *            The subscriber
	 */
	public void subscribe(String notificationName, INotificationListener listener) {
		WeakList<INotificationListener> listeners = this.subscribers.get(notificationName);
		if (listeners == null) {
			listeners = new WeakArrayList<INotificationListener>();
			this.subscribers.put(notificationName, listeners);
		}
		listeners.add(listener);
	}

	/**
	 * Un-subscribe from a {@link Notification}
	 * 
	 * @param notificationName
	 *            The name of the {@link Notification} to un-subscribe from
	 * @param listener
	 *            The subscriber
	 */
	public void unsubscribe(String notificationName, INotificationListener listener) {
		final WeakList<INotificationListener> listeners = this.subscribers.get(notificationName);
		if (listeners != null) {
			listeners.remove(listener);
		}
	}

}
