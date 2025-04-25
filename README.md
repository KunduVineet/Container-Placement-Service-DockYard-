# Container Placement Service DockYard API

## Project Overview
This project implements a simple Java REST API that optimizes container placement in a shipping yard. The core algorithm uses mathematical scoring rules to determine the best available slot for each incoming container based on multiple factors like distance, size compatibility, refrigeration needs, and availability.

## Problem Statement
At busy shipping ports, containers need to be placed efficiently to minimize delays. A mathematician designed scoring rules to identify optimal placement, and my job was to implement these rules in a functional Java API that returns placement recommendations.

## Tech Stack
- Java 11
- Spring Boot 2.7.0
- Maven
- JUnit 5 (for testing)

## Key Features
- REST API endpoint for container placement recommendations
- Mathematical scoring algorithm for optimal slot selection
- Input validation and error handling
- Unit tests for core functionality

## How the Algorithm Works
For each container-slot pair, we calculate a score based on:

1. **Distance**: Manhattan distance between crane and slot (|x1-x2| + |y1-y2|)
2. **Size Fit**: Penalty of 10,000 if container doesn't fit in slot
3. **Cold Storage**: Penalty of 10,000 if refrigeration needs aren't met
4. **Occupancy**: Penalty of 10,000 if slot is already occupied

The slot with the lowest score is chosen. If all slots have scores ≥ 10,000, we return "no suitable slot".

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── controller/
│   │   │   └── PlacementController.java
│   │   ├── model/
│   │   │   ├── Container.java
│   │   │   ├── PlacementRequest.java
│   │   │   ├── PlacementResponse.java
│   │   │   └── Slot.java
│   │   └── service/
│   │       └── ContainerPlacementService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── TestCases/
            └── ContainerPlacementServiceTest.java
```

## Installation & Setup
1. Clone this repository or download the source code
2. Make sure you have JDK 11+ and Maven installed
3. Navigate to project root and run:
```bash
mvn clean install
```

## How to Run
Start the application using:
```bash
mvn spring-boot:run
```
The API will be available at `http://localhost:8080`

## API Usage
### Endpoint
`POST /api/pickSpot`

### Sample Request
```bash
curl -X POST http://localhost:8080/api/pickSpot \
  -H "Content-Type: application/json" \
  -d '{
    "container": {
      "id": "C1",
      "size": "small", 
      "needCold": false,
      "x": 1, 
      "y": 1
    },
    "yardMap": [
      { 
        "x": 1,
        "y": 2, 
        "sizeCap": "small",
        "hasColdUnit": false,
        "occupied": false 
      },
      { 
        "x": 2,
        "y": 2, 
        "sizeCap": "big", 
        "hasColdUnit": true, 
        "occupied": false 
      }
    ]
}'
```

### Sample Response (Success)
```json
{
  "containerId": "C1",
  "targetX": 1,
  "targetY": 2
}
```

### Sample Response (No Suitable Slot)
```json
{
  "error": "no suitable slot"
}
```

## Testing
The project includes unit tests for the core placement logic. Run tests using:
```bash
mvn test
```

## Implementation Highlights
- The placement service calculates scores for each slot based on multiple criteria
- We use a simple loop to find the slot with minimum score
- The controller properly handles JSON parsing and error conditions
- Used Spring Boot for quick development and clean architecture

## Future Improvements
1. Add more comprehensive error handling
2. Implement caching for frequently accessed data
3. Improve performance for larger yards using spatial indexing
4. Add authentication for API security
5. Create a simple frontend visualization

## Notes for Developers
- The scoring system uses 10,000 as an "invalid" penalty to ensure invalid slots are never chosen
- Manhattan distance is used instead of Euclidean to match real-world crane movement
- The algorithm completes in under 200ms for yards up to 20x20 grid size

## Conclusion
This project demonstrates implementing a mathematical algorithm as a REST API. While simple, it shows how backend services can wrap complex logic in an accessible interface that other systems can easily consume.
