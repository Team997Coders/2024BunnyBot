package frc.robot.lib;

import edu.wpi.first.wpilibj.I2C;

/**
 * Driver for the APDS-9151 color sensor. 
 * Reads ambient light and RGB values from the sensor.
 */
public class ColorSensor {
    private static final int APDS9151_I2C_ADDRESS = 0x39; // Default I2C address for the APDS-9151
    private static final int ENABLE_REGISTER = 0x80;
    private static final int ATIME_REGISTER = 0x81;
    private static final int CONTROL_REGISTER = 0x8F;
    private static final int COLOR_DATA_REGISTER = 0x94; // Starting address for red, green, blue, and clear values

    private final I2C i2c;

    public ColorSensor(I2C.Port port) {
        i2c = new I2C(port, APDS9151_I2C_ADDRESS);
        initializeSensor();
    }

    /**
     * Initializes the APDS-9151 by enabling the color sensor and configuring the integration time.
     */
    private void initializeSensor() {
        // Power ON and Enable RGBC (ALS) sensor (bits: 0x03)
        writeRegister(ENABLE_REGISTER, 0x03);
        // Set the ATIME (integration time) to maximum (0xFF = 2.4ms, lower values = longer time)
        writeRegister(ATIME_REGISTER, 0xF6); // Set integration time to 101ms
        // Set the Control register for gain (0x01 = 4x gain, 0x02 = 16x gain, 0x03 = 64x gain, 0x00 = 1x)
        writeRegister(CONTROL_REGISTER, 0x01); // Gain of 4x
    }

    /**
     * Writes a single byte to the given register on the sensor.
     *
     * @param register The register address to write to.
     * @param value    The byte value to write.
     */
    private void writeRegister(int register, int value) {
        i2c.write(register, value);
    }

    /**
     * Reads 2 bytes (16 bits) from the given register and combines them into a single value.
     *
     * @param register The starting register address to read from.
     * @return The 16-bit value from the two consecutive registers.
     */
    private int read16BitRegister(int register) {
        byte[] buffer = new byte[2];
        i2c.read(register, 2, buffer);
        // Combine the two bytes into a 16-bit integer (little-endian)
        return (buffer[1] & 0xFF) << 8 | (buffer[0] & 0xFF);
    }

    /**
     * Reads the ambient light, red, green, and blue color data from the sensor.
     *
     * @return A ColorData object containing the ambient light, red, green, and blue values.
     */
    public ColorData getColorData() {
        int clear = read16BitRegister(COLOR_DATA_REGISTER);
        int red = read16BitRegister(COLOR_DATA_REGISTER + 2);
        int green = read16BitRegister(COLOR_DATA_REGISTER + 4);
        int blue = read16BitRegister(COLOR_DATA_REGISTER + 6);
        return new ColorData(clear, red, green, blue);
    }

    /**
     * Data class to store the ambient light, red, green, and blue color values.
     */
    public static class ColorData {
        public final int ambientLight;
        public final int red;
        public final int green;
        public final int blue;

        public ColorData(int ambientLight, int red, int green, int blue) {
            this.ambientLight = ambientLight;
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        @Override
        public String toString() {
            return String.format("Ambient: %d, Red: %d, Green: %d, Blue: %d", ambientLight, red, green, blue);
        }
    }
}
