package com.company.innerclass;

import com.company.enums.Brand;
import com.company.enums.Color;
import com.company.exceptions.InactivityGameConsoleException;
import com.company.interfaces.IPowered;
import com.company.nested_static_class.Game;

public class GameConsole implements IPowered {
    private static final int MAX_WAITING_COUNTER = 5;

    private final Brand brand;
    private final String model;
    private final String serial;
    private Gamepad firstGamepad;
    private Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;


    public class Gamepad implements IPowered {
        private final Brand brand;
        private final String consoleSerial;
        private int connectedNumber;
        private Color color;
        private float chargeLevel;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber) {
            this(brand, connectedNumber, Color.WHITE, 100.0f, false);
        }

        public Gamepad(Brand brand, int connectedNumber, Color color, float chargeLevel, boolean isOn) {
            this.brand = brand;
            this.consoleSerial = serial;
            this.connectedNumber = connectedNumber;
            this.color = color;
            this.chargeLevel = chargeLevel;
            this.isOn = isOn;
        }

        @Override
        public void powerOn() {
            this.isOn = true;
            GameConsole.this.setOn(true);  // автоматически вкл консоль
        }

        @Override
        public void powerOff() {
            this.isOn = false;
            GameConsole.this.swapGamepadsIfNeeded();  // меняем местами джостики если нужно
        }

        public Brand getBrand() {
            return brand;
        }

        public String getConsoleSerial() {
            return consoleSerial;
        }

        public int getConnectedNumber() {
            return connectedNumber;
        }

        public Color getColor() {
            return color;
        }

        public float getChargeLevel() {
            return chargeLevel;
        }

        public boolean isOn() {
            return isOn;
        }

        public void setConnectedNumber(int connectedNumber) {
            this.connectedNumber = connectedNumber;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setChargeLevel(float chargeLevel) {
            if (chargeLevel <= 0) {
                chargeLevel = 0;
                powerOff();  // выкл джойстик
            }
            this.chargeLevel = chargeLevel;
        }

        public void setOn(boolean on) {
            isOn = on;
        }
    }


    public GameConsole(Brand brand, String serial) {
        this(brand, "", serial, false);
    }

    public GameConsole(Brand brand, String model, String serial, boolean isOn) {
        this.brand = brand;
        this.model = model;
        this.serial = serial;
        this.firstGamepad = new Gamepad(this.brand, 1);
        this.secondGamepad = new Gamepad(this.brand, 2);
        this.isOn = isOn;
    }


    @Override
    public void powerOn() {
        isOn = true;
    }

    @Override
    public void powerOff() {
        isOn = false;
    }

    public void turnOnFirstGamepad() {
        firstGamepad.powerOn();
    }

    public void turnOnSecondGamepad() {
        secondGamepad.powerOn();
    }

    public void swapGamepadsIfNeeded() {
        if (isOn && !firstGamepad.isOn && secondGamepad.isOn) {
            Gamepad temp = firstGamepad;
            firstGamepad = secondGamepad;
            firstGamepad.setConnectedNumber(1);
            secondGamepad = temp;
            secondGamepad.setConnectedNumber(2);
        }
    }

    public void loadGame(Game game) {
        if (isOn) {
            System.out.println("Игра " + game.getName() + " загружается...");
            setActiveGame(game);
        }
        else {
            System.out.println("Консоль выключена");
        }
    }

    public void playGame() {
        if (isOn) {
            try {
                // проверка состояния джойстиков
                boolean isCorrect = checkStatus();
                if (!isCorrect) {
                    return;
                }

                System.out.println("Играем в " + activeGame.getName());

                // выводим и уменьшаем заряд актианых джойстиков
                System.out.print("Заряд активных джойстиков: ");
                if (firstGamepad.isOn) {
                    System.out.print("1) " + firstGamepad.chargeLevel + "% ");
                    firstGamepad.setChargeLevel(firstGamepad.chargeLevel - 10);
                }
                if (secondGamepad.isOn) {
                    System.out.print("2) " + secondGamepad.chargeLevel + "%");
                    secondGamepad.setChargeLevel(secondGamepad.chargeLevel - 10);
                }
                System.out.println();
            }
            catch (InactivityGameConsoleException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Консоль выключена");
        }
    }


    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setFirstGamepad(Gamepad firstGamepad) {
        this.firstGamepad = firstGamepad;
    }

    public void setSecondGamepad(Gamepad secondGamepad) {
        this.secondGamepad = secondGamepad;
    }

    public void setOn(boolean on) {
        if (isOn != on) {
            isOn = on;

            // если приставку выкл, то и выкл все джойстики
            if (!isOn) {
                firstGamepad.powerOff();
                secondGamepad.powerOff();
            }
        }
    }

    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
    }


    private boolean checkStatus() throws InactivityGameConsoleException {
        if (!firstGamepad.isOn && !secondGamepad.isOn) {
            System.out.println("Подключите джойстик");
            if (waitingCounter >= MAX_WAITING_COUNTER) {
                setOn(false);
                throw new InactivityGameConsoleException("Приставка завершает работу из-за отсутствия активности", MAX_WAITING_COUNTER);
            }
            waitingCounter++;
            return false;
        }
        waitingCounter = 0;
        return true;
    }
}
