package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private int timer;
	private Thread thread;

	public void TrainControllerImpl(){
		timer = 2;
		thread = new Thread(){
			public void run(){
				thread.run();
			try{
				followSpeed();
				while(timer > 0){
					if(timer == 0){
						timer+=2;
					}
					timer--;
					
				}
			}
			catch(){}
			}
		};
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	public void emergencyBreak(){
		if(speedLimit == 0 && referenceSpeed >= 200){
			//If there is no speed limit and the current speed is too fast, the system makes an emergency breaking
			referenceSpeed = 0;
		}
	}
}
