import java.util.List;

public class Main {
    record ServiceMetric(String service, int latencyMs, double errorRate, int cpuPercent) {}

    public static void main(String[] args) {
        List<ServiceMetric> metrics = List.of(
                new ServiceMetric("orders-api", 180, 0.7, 62),
                new ServiceMetric("payments-api", 420, 2.8, 84),
                new ServiceMetric("customer-api", 210, 0.9, 58));

        System.out.println("=== Deployment Health Monitor ===");
        int healthy = 0;
        for (ServiceMetric metric : metrics) {
            String status = evaluate(metric);
            if (status.equals("HEALTHY")) healthy++;
            System.out.printf("%-15s latency=%4dms error=%4.1f%% cpu=%3d%% -> %s%n",
                    metric.service(), metric.latencyMs(), metric.errorRate(), metric.cpuPercent(), status);
        }

        System.out.println();
        if (healthy == metrics.size()) {
            System.out.println("Recommendation: APPROVE deployment.");
        } else {
            System.out.println("Recommendation: HOLD deployment and investigate unhealthy services.");
        }
    }

    static String evaluate(ServiceMetric metric) {
        if (metric.latencyMs() > 350 || metric.errorRate() > 2.0 || metric.cpuPercent() > 80) {
            return "UNHEALTHY";
        }
        if (metric.latencyMs() > 250 || metric.errorRate() > 1.0 || metric.cpuPercent() > 70) {
            return "WARNING";
        }
        return "HEALTHY";
    }
}
