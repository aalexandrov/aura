package de.tuberlin.aura.demo.taskmanager;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import de.tuberlin.aura.core.iosystem.IOMessages.DataMessage;
import de.tuberlin.aura.demo.deployment.LocalDeployment;
import de.tuberlin.aura.taskmanager.Contexts.TaskContext;
import de.tuberlin.aura.taskmanager.TaskInvokeable;
import de.tuberlin.aura.taskmanager.TaskManager;

public class Task4 {

	public static final Logger LOG = Logger.getRootLogger();
	
	public static class Task4Exe extends TaskInvokeable {

		public Task4Exe( TaskContext context ) {
			super( context );
		}

		@Override
		public void execute() throws Exception {
		
			for( int i = 0; i < 100; ++i ) {			
				final BlockingQueue<DataMessage> inputMsgs = context.inputQueues.get( 0 );			
				try {			
					final DataMessage dm = inputMsgs.take();
					LOG.info( "received data message " + dm.messageID + " from task " + dm.srcTaskID );
				} catch (InterruptedException e) {
					LOG.info( e );
				}
			}
		}
	}

	public static class Task6Exe extends TaskInvokeable {

		public Task6Exe( TaskContext context ) {
			super( context );
		}

		@Override
		public void execute() throws Exception {
			for( int i = 0; i < 100; ++i ) {			
				final BlockingQueue<DataMessage> inputMsgs = context.inputQueues.get( 0 );			
				try {			
					final DataMessage dm = inputMsgs.take();
					LOG.info( "received data message " + dm.messageID + " from task " + dm.srcTaskID );
				} catch (InterruptedException e) {
					LOG.info( e );
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		final SimpleLayout layout = new SimpleLayout();
		final ConsoleAppender consoleAppender = new ConsoleAppender( layout );
		LOG.addAppender( consoleAppender );
		
		final TaskManager taskManager = new TaskManager( LocalDeployment.MACHINE_4_DESCRIPTOR ); 
		taskManager.installTask( LocalDeployment.TASK_4_DESCRIPTOR, LocalDeployment.TASK_4_BINDING, Task4Exe.class );
		taskManager.installTask( LocalDeployment.TASK_6_DESCRIPTOR, LocalDeployment.TASK_6_BINDING, Task6Exe.class );
	}
}
