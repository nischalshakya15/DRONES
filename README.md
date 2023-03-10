### Description

There is a major new technology that destined to be a disruptive force in the field of transportation: the drone. Just
as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone
has the potential to leapfrog traditional transportation infrastructure. Useful drone functions include delivery of
small items that are (urgently) needed in locations with difficult access.

--------------------------------------------------------------------------------------------------------------

### Build and Run the project

#### Requirements

* JDK 17
* Postman (for API Testing)
* IDE (as preferences)

#### Run the project

* Clone the project.
  ```shell
  git clone https://github.com/nischalshakya15/DRONES.git
  ```
* Navigate to the project directory.
  ```shell
  cd DRONES
  ```
* Build the project.Use **./mvnw** for linux or mac, and **mvnw.cmd** for windows.
  ```shell
  ./mvnw clean install
  ```
* Run the jar file.
  ```shell
  java -jar target/drones-0.0.1-SNAPSHOT.jar 
  ```

#### Run the test

* Run the below command to run the test.

```shell
./mvnw test
```

---------------------------------------------------------------------------------------------------------

#### Access to the API documentation

* Open the browser.
* Enter this URL.
  ```textmate
  http://localhost:8080/swagger-ui/index.html#/
  ```

----

#### Access to the H2 console

* Open the browser.
* Enter this URL.
  ```textmate
  http://localhost:8080/h2-console/
  ```
* username: **sa**, and password: **password** to authenticate the h2 database.

-------

### API endpoints

#### 01-Registering a drone.

**Method**: POST

**URL**: /api/v1/drones

**Body**:

```json
{
  "serialNumber": "001",
  "model": "HEAVY_WEIGHT"
}
```

**Response**:

```json
{
  "id": 11,
  "createdAt": "2023-03-07T10:42:07.843436777",
  "updatedAt": "2023-03-07T10:42:07.843462606",
  "serialNumber": "001",
  "model": "HEAVY_WEIGHT",
  "weightLimit": 500,
  "batteryPercentage": 100,
  "state": "IDLE",
  "charging": false
}
```

#### 02-Loading a drone with medication items.

**Method**: POST

**URL**: /api/v1/drones/medications

**Body**:

```json
{
  "name": "sHHIvOhN69LDAiWr9zDJQ4XYL_AGvh66-3u1cEUX2MRIdRRsX7eHgoZ4qQQbPxhWl",
  "weight": 500,
  "code": "3EKTZH_X",
  "imageURL": "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2020_49/3432121/201201-covid-vaccine-al-0900.jpg",
  "droneSerialNumber": "001"
}
```

**Response**:

```json
{
  "id": 13,
  "createdAt": "2023-03-07T10:46:22.415036738",
  "updatedAt": "2023-03-07T10:46:22.415050298",
  "name": "sHHIvOhN69LDAiWr9zDJQ4XYL_AGvh66-3u1cEUX2MRIdRRsX7eHgoZ4qQQbPxhWl",
  "weight": 500,
  "code": "3EKTZH_X",
  "imageURL": "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2020_49/3432121/201201-covid-vaccine-al-0900.jpg",
  "droneSerialNumber": "001",
  "state": "LOADING"
}
```

#### 03-Checking the loaded medication items for a given drone.

**Method**: GET

**URL**: /api/v1/drones/medications

**QueryParam** : ?serialNumber={serialNumberOfDrone}&medicationState={stateOfMedication}

**Response**:

```json
{
  "id": 11,
  "createdAt": "2023-03-07T10:42:07.843437",
  "updatedAt": "2023-03-07T10:48:41.084369",
  "serialNumber": "001",
  "model": "HEAVY_WEIGHT",
  "weightLimit": 500,
  "batteryPercentage": 100,
  "state": "LOADED",
  "charging": false,
  "medications": [
    {
      "id": 13,
      "createdAt": "2023-03-07T10:46:22.415037",
      "updatedAt": "2023-03-07T10:48:41.079835",
      "name": "sHHIvOhN69LDAiWr9zDJQ4XYL_AGvh66-3u1cEUX2MRIdRRsX7eHgoZ4qQQbPxhWl",
      "weight": 500,
      "code": "3EKTZH_X",
      "imageURL": "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2020_49/3432121/201201-covid-vaccine-al-0900.jpg",
      "droneSerialNumber": "001",
      "state": "LOADED"
    }
  ]
}
```

#### 04-Checking available drones for loading.

**Method**: GET

**URL**: /api/v1/drones

**QueryParam** : ?state={droneState}

**Response**:

```json
[
  {
    "id": 1,
    "createdAt": "2023-03-05T07:17:34.782565",
    "updatedAt": "2023-03-05T07:17:34.782754",
    "serialNumber": "SN123456789ABC",
    "model": "LIGHT_WEIGHT",
    "weightLimit": 500,
    "batteryPercentage": 100,
    "state": "IDLE",
    "charging": false
  },
  {
    "id": 2,
    "createdAt": "2023-03-05T07:23:11.418603",
    "updatedAt": "2023-03-05T07:23:11.418629",
    "serialNumber": "YUNTYHBSA123456",
    "model": "LIGHT_WEIGHT",
    "weightLimit": 500,
    "batteryPercentage": 100,
    "state": "IDLE",
    "charging": false
  }
]
```

#### 05-Check the drone battery level for a given drone.

**Method**: GET

**URL**: /api/v1/drones/serial-numbers

**QueryParam** : ?serialNumber={serialNumberOfDrone}

**Response**:

```json
{
  "id": 11,
  "createdAt": "2023-03-07T10:42:07.843437",
  "updatedAt": "2023-03-07T11:00:26.424418",
  "serialNumber": "001",
  "model": "HEAVY_WEIGHT",
  "weightLimit": 500,
  "batteryPercentage": 75,
  "state": "IDLE",
  "charging": true
}
```

------------------------------------------

### Battery consumption calculation formula.

|MODEL |Consumption by distance coverage (1M)|Consumption by weight (1g)|Battery Consumption|
|------|-----------------------------|--------------------------------|----------------------|
|LIGHT_WEIGHT   |0.007               |0.07                        |49|
|MIDDLE_WEIGHT  |0.006               |0.06                        |42|
|HEAVY_WEIGHT   |0.005               |0.05                        |35|
|CRUISER_WEIGHT |0.008               |0.08                        |56|

**total_battery_consumption = (1M distance coverage * distance * 2) + (1g charge consumption * Weight of medication
items)**

Here, 2 indicate the drone travel from source to destination and vice versa.

------------------------------------------

### Assumed Functional Requirements.
+ The medication items in the drone can only be loaded if it is not in the below state.
  + Drone is not charging.
  + Drone is IDLE.
  + Drone battery is less than 25%.
  + Drone doesn't have enough battery to carry the medication items from source to destination.
+ Once medication items are loaded into Drone. The state of both the medication and the drone changes every 5 seconds.
+ Medication state change from **LOADING - LOADED - LOADED - DELIVERING - DELIVERING - DELIVERED.**
+ Drone state change from **LOADING - LOADED - LOADED - DELIVERING - DELIVERING - DELIVERED - DELIVERED - RETURNING - RETURNING - IDLE.**
+ Drone battery will be charged automatically if the drone state is IDLE, and the battery is less than 100 percent.
+ Drone battery will be increased by 10% every 10 seconds.