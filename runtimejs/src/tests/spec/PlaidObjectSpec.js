describe("PlaidObject", function() {
  var obj1;
  var obj2;
  var obj3;

  var treeBuilder=new Tree();
  var car = new WithNode("car",[],treeBuilder.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md1 = treeBuilder.toMetadata();

  var treeBuilder2=new Tree();
  var brakingStatus = new WithNode("brakingStatus",[],treeBuilder2.root);
  var braking = new CaseOfNode("notBraking",["startBraking"],brakingStatus);
  var md2 = treeBuilder2.toMetadata();

  var treeBuilder3=new Tree();
  var car = new WithNode("car",[],treeBuilder3.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("notBraking",["startBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md3 = treeBuilder3.toMetadata();

  var membersAtStart1=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
  var membersAtStart2=["startBraking"];
  var membersAtStart3=["speed","acceleration","stopDriving","startBraking","straight","turnRight","getDirty"];


  beforeEach(function() {
    obj1 = new PlaidObject(md1.clone());
    obj2 = new PlaidObject(md2.clone());
    obj3 = new PlaidObject(md3.clone());
    var length1=membersAtStart1.length;
    for (var i=0;i<length1;i++){
      obj1[membersAtStart1[i]]=10;
    }   
    var length2=membersAtStart2.length;
    for (var i=0;i<length2;i++){
      obj2[membersAtStart2[i]]=10;
    }
    var length3=membersAtStart3.length;
    for (var i=0;i<length3;i++){
      obj3[membersAtStart3[i]]=10;
    }
    
  });

  it("should be able to match a tag in it", function() {
    var result=obj1.match("turningLeft");
    expect(result).toBe(true);
  });

  it("should fail to match a tag not in it", function() {
    var result=obj1.match("lksjlkdj");
    expect(result).toBe(false);
  });

  it("should have the expected tag list", function() {
    var result=obj1.tags();
    var desiredTags=[ 'car', 'drivingStatus', 'driving', 'brakingStatus', 'braking', 'directionStatus', 'turningLeft', 'cleanStatus', 'clean' ];
    expect(result).toHaveSameElementsAs(desiredTags);
  });

  it("should have the expected member list", function() {
    var result=obj1.members();
    expect(result).toHaveSameElementsAs(membersAtStart1);
  });

  it("should have correct tree and members after replace", function() {
    obj1.replace(obj2); 
    var result=obj1.tree;
    expect(result).toEqual(obj2.tree);   
    var obj1Members=[]
    for (var item in obj1){
      obj1Members.push(item);
    }
    var obj2Members=[]
    for (var item in obj2){
      obj2Members.push(item);
    }
    expect(obj1Members).toEqual(obj2Members);
  });

  it("should have correct tree and members after state change", function() {
    obj1.stateChange(obj2);
    var result=obj1.tree;
    expect(result).toEqual(obj3.tree);
    var obj1Members=[]
    for (var item in obj1){
      obj1Members.push(item);
    }
    var obj3Members=[]
    for (var item in obj3){
      obj3Members.push(item);
    }
    expect(obj1Members).toHaveSameElementsAs(obj3Members);
  });
  
});