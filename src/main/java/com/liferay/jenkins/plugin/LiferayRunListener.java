package com.liferay.jenkins.plugin;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

@Extension
public class LiferayRunListener extends RunListener<AbstractBuild> {

	public LiferayRunListener() {
		super(AbstractBuild.class);

		_log.debug("LiferayRunListener DEBUG");
		_log.info("LiferayRunListener INFO");

		System.out.println("LiferayRunListener - Hello world!");
	}

	@Override
	public void onStarted(AbstractBuild build, TaskListener listener) {
		LiferayPublisher publisher = getLiferayNotifier(build);

		if (publisher == null) {
			return;
		}

		System.out.println("onStarted - publisher=" + publisher);
		System.out.println("onStarted - publisher.webHookUrl=" + publisher.webHookUrl);
		System.out.println("onStarted - build.getAbsoluteUrl()=" + build.getAbsoluteUrl());
		System.out.println("onStarted - build.getProject().getDisplayName()=" + build.getProject().getDisplayName());
		System.out.println("onStarted - build.getDisplayName()=" + build.getDisplayName());
		System.out.println("onStarted - build.getBuildVariables().toString()=" + build.getBuildVariables().toString());
	}

	@Override
	public void onCompleted(AbstractBuild build, @Nonnull TaskListener listener) {
		LiferayPublisher publisher = getLiferayNotifier(build);

		if (publisher == null) {
			return;
		}

		System.out.println("onCompleted - publisher=" + publisher);
		System.out.println("onCompleted - publisher.webHookUrl=" + publisher.webHookUrl);
		System.out.println("onCompleted - build.getAbsoluteUrl()=" + build.getAbsoluteUrl());
		System.out.println("onCompleted - build.getProject().getDisplayName()=" + build.getProject().getDisplayName());
		System.out.println("onCompleted - build.getDisplayName()=" + build.getDisplayName());
		System.out.println("onCompleted - build.getBuildVariables().toString()=" + build.getBuildVariables().toString());
	}

	private LiferayPublisher getLiferayNotifier(AbstractBuild build) {
		for (Object publisher : build.getProject().getPublishersList().toMap().values()) {
			if (publisher instanceof LiferayPublisher) {
				return (LiferayPublisher) publisher;
			}
		}

		return null;
	}


	private static final Logger _log = LoggerFactory.getLogger(
		LiferayRunListener.class);

}