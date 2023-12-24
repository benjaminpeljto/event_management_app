import { EventForList  } from '../../utils/types'

type Props = {
    event: EventForList
}

const EventCard = ({ event }: Props) => {
    return (
      <div className="card mb-4" style={{ maxWidth: '400px' }}>
        <div className="card-body">
          <h5 className="card-title">{event.name}</h5>
          <p className="card-text">{event.description}</p>
          <ul className="list-group list-group-flush">
            <li className="list-group-item">
              <strong>Location:</strong> {event.location}
            </li>
            <li className="list-group-item">
              <strong>Category:</strong> {event.EventCategory}
            </li>
            <li className="list-group-item">
              <strong>Occurrence:</strong> {event.occurrence}
            </li>
            <li className="list-group-item">
              <strong>Total Available Seats:</strong> {event.totalAvailableSeats}
            </li>
          </ul>
          <button className="btn btn-primary mt-3">View Details</button>
        </div>
      </div>
    );
  };

export default EventCard