# BIRPI (BuiltIn Resourcepack Injector)

# Turns out after I finished making this I found out FAPI already has this built in, see ResourceManagerHelper.registerBuiltinResourcePack
Library mod allowing developers to add a built in resourpack bundled with their mods like th vanilla programmer art
See https://github.com/kyrptonaught/linkedstorage

Repo: 'https://dl.bintray.com/kyrptonaught/Birpi'
'net.kyrptonaught:birpi:<version>'  Latest version : [ ![Download](https://api.bintray.com/packages/kyrptonaught/Birpi/birpi/images/download.svg) ](https://bintray.com/kyrptonaught/Birpi/birpi/_latestVersion)
  
  The resourcepack should be placed in "/resources/resourcepacks/" as .zip files. 
  
  See https://github.com/kyrptonaught/linkedstorage/tree/master/src/main/resources/resourcepacks

1) Add the entrypoint in your fabric.mod.json: "registerbirpis"
2) Implement RegisterBIRPI 
3) Register your birpis in the provided method, example: 
```java
@Override
    public List<BIRPIdata> getBIRPIs() {
        List<BIRPIdata> birpidata = new ArrayList<>();
        birpidata.add(new BIRPIdata(LinkedStorageMod.MOD_ID, "enderstorage", "enderstorage.zip","EnderStorage for LinkedStorage",this.getClass()));
        return birpidata;
    }
  ```
LinkedStorage.MOD_ID = your mod's id 

"enderstorage"= the id used to register the birpi

"enderstorage.zip" the filename of the resourcepack

"EnderStorage for LinkedStorage" = description

this.getClass() = an instance of your main mod class(used for grabing the resourcepack as an inputstream from the mod jar)

![ingame](https://i.imgur.com/4j24V6F.png)
