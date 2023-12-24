import EventCard from './components/EventCard'
import { EventForList } from './utils/types';

const event: EventForList = {
  id: "33333",
  name: "Billiard event",
  description: "Experience exellence in Sarajevo Billiard days",
  location: "Zetra, Sarajevo",
  EventCategory: "Sports",
  occurrence: "22.12.2023",
  totalAvailableSeats: 300
};

function App() {
  /*const [count, setCount] = useState(0)*/

  return (
    <>
      <EventCard event ={ event }/>
    </>
  )
}

export default App

