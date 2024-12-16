    package main.classes;

    import java.time.DayOfWeek;
    import java.time.LocalDateTime;

    public enum consultationFee {
        // Regular consultation fees 
        GENERAL_CONSULTATION(3000.00, "General Consultation", false, 30),
        SPECIALIST_CONSULTATION(5000.00, "Specialist Consultation", false, 45),
        FOLLOW_UP(2000.00, "Follow-Up Consultation", false, 20),

        // Special consultation types
        EMERGENCY_CONSULTATION(6000.00, "Emergency Consultation", true, 60),
        TELEMEDICINE(2500.00, "Telemedicine Consultation", false, 30),

        // Extended consultation fees
        EXTENDED_CONSULTATION(5500.00, "Extended Consultation", false, 60),

        // Special categories
        PEDIATRIC_CONSULTATION(4000.00, "Pediatric Consultation", false, 45),
        GERIATRIC_CONSULTATION(4000.00, "Geriatric Consultation", false, 45),

        // New specialized categories
        MENTAL_HEALTH_CONSULTATION(5000.00, "Mental Health Consultation", false, 50),
        PHYSICAL_THERAPY_SESSION(3500.00, "Physical Therapy Session", false, 60),
        NUTRITIONAL_CONSULTATION(3000.00, "Nutritional Consultation", false, 40);

        private final double baseFee;
        private final String description;
        private final boolean emergency;
        private final int durationMinutes;
        private static final double WEEKEND_SURCHARGE = 1.2; // 20% extra for weekends
        private static final double AFTER_HOURS_SURCHARGE = 1.5; // 50% extra for after hours
        private static final double HOLIDAY_SURCHARGE = 2.0; // 100% extra for holidays

        // Constructor
        consultationFee(double baseFee, String description, boolean emergency, int durationMinutes) {
            this.baseFee = baseFee;
            this.description = description;
            this.emergency = emergency;
            this.durationMinutes = durationMinutes;
        }

        // Enhanced getters
        public double getBaseFee() {
            return baseFee;
        }

        public String getDescription() {
            return description;
        }

        public boolean isEmergency() {
            return emergency;
        }

        public int getDurationMinutes() {
            return durationMinutes;
        }

        // Calculate actual fee based on various factors
        public double calculateFee(LocalDateTime consultationTime, boolean isHoliday) {
            double finalFee = baseFee;

            // Apply weekend surcharge
            if (isWeekend(consultationTime)) {
                finalFee *= WEEKEND_SURCHARGE;
            }

            // Apply after-hours surcharge (before 9 AM or after 5 PM)
            if (isAfterHours(consultationTime)) {
                finalFee *= AFTER_HOURS_SURCHARGE;
            }

            // Apply holiday surcharge
            if (isHoliday) {
                finalFee *= HOLIDAY_SURCHARGE;
            }

            return Math.round(finalFee * 100.0) / 100.0; // Round to 2 decimal places
        }

        // Utility method to get fee with full description including duration
        public String getFullDescription() {
            return String.format("%s (Duration: %d mins) - Base Fee: %,.2f DZD", 
                description, durationMinutes, baseFee);
        }

        // Enhanced method to find fee by type with additional validation
        public static consultationFee getByType(String type) {
            if (type == null || type.trim().isEmpty()) {
                throw new IllegalArgumentException("The consultation type cannot be null or empty");
            }

            try {
                return valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid consultation type: " + type + 
                    ". Available types: " + getAvailableTypes());
            }
        }

        // Helper method to get all available consultation types
        public static String getAvailableTypes() {
            StringBuilder types = new StringBuilder();
            for (consultationFee fee : values()) {
                types.append(fee.name()).append(", ");
            }
            return types.substring(0, types.length() - 2);
        }

        // Check if consultation time is on weekend
        private boolean isWeekend(LocalDateTime dateTime) {
            return dateTime.getDayOfWeek() == DayOfWeek.FRIDAY || 
                dateTime.getDayOfWeek() == DayOfWeek.SATURDAY;
        }

        // Check if consultation time is after hours
        boolean isAfterHours(LocalDateTime dateTime) {
            int hour = dateTime.getHour();
            return hour < 9 || hour >= 17;
        }

        // Get estimated wait time based on emergency status
        public String getEstimatedWaitTime() {
            if (emergency) {
                return "Immediate attention required";
            }
            return String.format("Approximately %d minutes", durationMinutes);
        }

        // Get hourly rate
        public double getHourlyRate() {
            return (baseFee / durationMinutes) * 60.0;
        }

        // Format fee for display
        public String getFormattedFee() {
            return String.format("%,.2f DZD", baseFee);
        }

        // Check if consultation type is suitable for telemedicine
        public boolean isSuitableForTelemedicine() {
            return this == TELEMEDICINE || 
                this == FOLLOW_UP || 
                this == MENTAL_HEALTH_CONSULTATION;
        }

        @Override
        public String toString() {
            return String.format("%s - %s (Duration: %d mins, Base Fee: %,.2f DZD)", 
                name(), description, durationMinutes, baseFee);
        }
    }

