package student.adventure;

import com.google.gson.annotations.SerializedName;

public class Direction {
    private String directionName;
    @SerializedName("room")
    private String roomName;

    public String getDirectionName() { return directionName; }

    public String getRoomName() { return roomName; }
}
