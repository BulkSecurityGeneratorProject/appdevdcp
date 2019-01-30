import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
interface IGoogleMapsLinkProps {
  lat1: number;
  long1: number;
  lat2?: number;
  long2?: number;
  label?: string;
}

function getDistanceFromLatLongInMeters(lat1: number, long1: number, lat2: number, long2: number) {
  const R = 6371; // Radius of the earth in km
  const dLat = deg2rad(lat2 - lat1); // deg2rad below
  const dLon = deg2rad(long2 - long1);
  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const d = R * c * 1000; // Distance in meters
  return d;
}

function deg2rad(deg) {
  return deg * (Math.PI / 180);
}

export const GoogleMapsLink = ({ lat1, long1, lat2, long2, label }: IGoogleMapsLinkProps) => {
  if (lat2 && long2) {
    const dist = getDistanceFromLatLongInMeters(lat1, long1, lat2, long2);
    label = label ? `${label} - a ${dist}m da loja` : `a ${dist}m da loja`;
  }
  return (
    <a href={`http://maps.google.com/maps?q=${lat1},${long1}`} target="_blank">
      <FontAwesomeIcon icon="map-marked-alt" />
      &nbsp;
      <span className="d-none d-md-inline">{label}</span>
    </a>
  );
};
