package Model;

public class GetItineraryModel {
    String id, Agent, GroupX, Mall, Address, Status, AgentID;



    public GetItineraryModel(String id, String Agent, String GroupX, String Mall, String Address, String Status, String AgentID){
       this.id = id;
       this.Agent = Agent;
       this.GroupX = GroupX;
       this.Mall = Mall;
       this.Address = Address;
       this.Status = Status;
       this.AgentID = AgentID;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgent() {
        return Agent;
    }

    public void setAgent(String agent) {
        Agent = agent;
    }

    public String getGroupX() {
        return GroupX;
    }

    public void setGroupX(String groupX) {
        GroupX = groupX;
    }

    public String getMall() {
        return Mall;
    }

    public void setMall(String mall) {
        Mall = mall;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAgentID() {
        return AgentID;
    }

    public void setAgentID(String agentID) {
        AgentID = agentID;
    }
}
