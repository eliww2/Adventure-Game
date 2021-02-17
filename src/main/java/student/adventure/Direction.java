package student.adventure;

import com.google.gson.annotations.SerializedName;

public class Direction {
    private String directionName;
    @SerializedName("room")
    private String roomName;

    /**
     * @return directionName
     */
    public String getDirectionName() { return directionName; }

    /**
     * @return roomName
     */
    public String getRoomName() { return roomName; }
}
