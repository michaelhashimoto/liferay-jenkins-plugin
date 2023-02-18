package com.liferay.jenkins.plugin;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import org.kohsuke.stapler.DataBoundConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LiferayPublisher extends Notifier {

	public String webHookUrl;

	@DataBoundConstructor
	public LiferayPublisher(String webHookUrl) {
		super();

		this.webHookUrl = webHookUrl;

		_log.debug("LiferayNotifier DEBUG");
		_log.info("LiferayNotifier INFO");

		System.out.println("LiferayNotifier - Hello world!");
	}


	@Override
	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}

	@Override
	public boolean perform(
			AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener)
		throws InterruptedException, IOException {

		return true;
	}

	@Override
	public LiferayBuildStepDescriptor getDescriptor() {
		return (LiferayBuildStepDescriptor) super.getDescriptor();
	}

	@Extension
	public static class LiferayBuildStepDescriptor
		extends BuildStepDescriptor<Publisher> {

		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return true;
		}

		@Override
		public String getDisplayName() {
			return "Liferay Jenkins notification";
		}

	}

	private static final Logger _log = LoggerFactory.getLogger(
		LiferayPublisher.class);

}
