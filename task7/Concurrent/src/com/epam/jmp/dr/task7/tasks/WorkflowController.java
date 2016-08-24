package com.epam.jmp.dr.task7.tasks;

/**
 * Contains flag, that says that all producers are stopped
 *
 */
public class WorkflowController {

	private boolean generationStopped;

	public WorkflowController() {
		generationStopped = false;
	}

	public void setGenerationStopped() {
		generationStopped = true;
	}

	public boolean isGenerationStopped() {
		return generationStopped;
	}

}
